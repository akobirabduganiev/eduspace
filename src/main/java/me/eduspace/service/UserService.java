package me.eduspace.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.bucket.BucketName;
import me.eduspace.dto.user.UserRequestDTO;
import me.eduspace.dto.user.UserResponseDTO;
import me.eduspace.entity.ConfirmationTokenEntity;
import me.eduspace.entity.UserEntity;
import me.eduspace.enums.ConfirmationStatus;
import me.eduspace.enums.UserRole;
import me.eduspace.enums.UserStatus;
import me.eduspace.exceptions.AppBadRequestException;
import me.eduspace.exceptions.ItemAlreadyExistsException;
import me.eduspace.exceptions.ItemNotFoundException;
import me.eduspace.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static me.eduspace.util.AmazonUtil.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final FileStoreService fileStoreService;

    /*public UserResponseDTO create(UserRequestDTO dto){
        var optional = userRepository.findByEmail(dto.getEmail());

        if (optional.isPresent())
            throw new ItemAlreadyExistsException("email already exists!");

        var entity=new UserEntity();
        *//*@NotNull(message = "name request!")
        private String name;
        @NotNull(message = "surname request!")
        private String surname;
        @NotNull(message = "phone request!")
        private String phone;
        @NotNull(message = "email request!")
        private String email;
        @NotNull(message = "password request!")
        private String password;
        private LocalDate birthDate;
        private UserRole role;*//*
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

    }*/

    public String signUpUser(UserEntity entity) {

        var optional = userRepository.findByEmail(entity.getEmail());

        if (optional.isPresent())
            throw new ItemAlreadyExistsException("email already exists!");

        var encodedPassword = bCryptPasswordEncoder
                .encode(entity.getPassword());

        entity.setPassword(encodedPassword);

        userRepository.save(entity);

        var token = UUID.randomUUID().toString();

        var smsOptional = confirmationTokenService.getToken(token);
        smsOptional.ifPresent(confirmationTokenService::delete);

        var confirmationSms = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(2),
                ConfirmationStatus.ACTIVE,
                entity
        );

        confirmationTokenService.confirmationToken(confirmationSms);

        return token;
    }

    public void enableUser(String email) {
        userRepository.enableUser(email, UserStatus.ACTIVE);
    }

    public void uploadUserProfileImage(String username, MultipartFile file) {
        isFileEmpty(file);
        isImage(file);
        UserEntity user = getUserByEmail(username);

        Map<String, String> metadata = extractMetadata(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getEmail());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStoreService.save(path, filename, Optional.of(metadata), file.getInputStream());
            userRepository.setImageLink(filename, user.getEmail());
            userRepository.updateLastModifiedDate(LocalDateTime.now(), user.getEmail());
        } catch (IOException e) {
            log.warn("upload not completed!", e);
            throw new AppBadRequestException("upload not completed!");
        }

    }

    public byte[] downloadUserProfileImage(String username) {
        UserEntity user = getUserByEmail(username);

        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getEmail());

        return user.getImageLink()
                .map(key -> fileStoreService.download(path, key))
                .orElse(new byte[0]);

    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ItemNotFoundException("user not found"));
    }

    public UserEntity checkOrGet(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("user not found {}", id);
                    throw new ItemNotFoundException("user not found");
                });
    }

}
