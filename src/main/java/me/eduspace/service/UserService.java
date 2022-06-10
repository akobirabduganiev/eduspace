package me.eduspace.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.bucket.BucketName;
import me.eduspace.entity.ConfirmationSmsEntity;
import me.eduspace.entity.UserEntity;
import me.eduspace.enums.ConfirmationStatus;
import me.eduspace.exceptions.AppBadRequestException;
import me.eduspace.exceptions.ItemAlreadyExistsException;
import me.eduspace.exceptions.ItemNotFoundException;
import me.eduspace.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static me.eduspace.util.AmazonUtil.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationSmsService confirmationSmsService;
    private final FileStoreService fileStoreService;

    public String signUpUser(UserEntity entity) {

        var optional = userRepository.findByPhone(entity.getPhone());

        if (optional.isPresent())
            throw new ItemAlreadyExistsException("phone number already exists!");

        var encodedPassword = bCryptPasswordEncoder
                .encode(entity.getPassword());

        entity.setPassword(encodedPassword);
        entity.setStatus(GeneralStatus.NOT_CONFIRMED);

        userRepository.save(entity);

        var sms = getRandomNumberString();

        var smsOptional = confirmationSmsService.getSms(sms);
        smsOptional.ifPresent(confirmationSmsService::delete);

        var confirmationSms = new ConfirmationSmsEntity(
                sms,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(2),
                ConfirmationStatus.ACTIVE,
                entity
        );

        confirmationSmsService.confirmationSms(confirmationSms);

        // TODO: an SMS code must be sent to the phone number

        return sms;
    }

    public void enableUser(String phone) {
        userRepository.enableUser(phone, GeneralStatus.ACTIVE);
    }
    public void uploadUserProfileImage(Long userProfileId, MultipartFile file) {
        isFileEmpty(file);
        isImage(file);
        UserEntity user = checkOrGet(userProfileId);

        Map<String, String> metadata = extractMetadata(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStoreService.save(path, filename, Optional.of(metadata), file.getInputStream());
            user.setImageLink(filename);
        } catch (IOException e) {
            log.warn("upload not completed!", e);
            throw new AppBadRequestException("upload not completed!");
        }

    }

    public byte[] downloadUserProfileImage(Long userProfileId) {
        UserEntity user = checkOrGet(userProfileId);

        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getId());

        return user.getImageLink()
                .map(key -> fileStoreService.download(path, key))
                .orElse(new byte[0]);

    }
    public UserEntity getUserByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(() -> new ItemNotFoundException("user not found"));
    }

    public UserEntity checkOrGet(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->{
                    log.info("user not found {}", id);
                    throw new ItemNotFoundException("user not found");
                });
    }

    public String getRandomNumberString() {

        var rnd = new Random();
        int number = rnd.nextInt(99999);

        return String.format("%05d", number);
    }
}
