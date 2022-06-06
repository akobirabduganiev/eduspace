package me.eduspace.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.entity.ConfirmationSmsEntity;
import me.eduspace.entity.UserEntity;
import me.eduspace.exceptions.ItemAlreadyExistsException;
import me.eduspace.exceptions.ItemNotFoundException;
import me.eduspace.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationSmsService confirmationSmsService;

    public String signUpUser(UserEntity userEntity) {
        var optional = userRepository.findByPhone(userEntity.getPhone());

        if (optional.isPresent())
            throw new ItemAlreadyExistsException("phone number already exists!");

        var encodedPassword = bCryptPasswordEncoder
                .encode(userEntity.getPassword());

        userEntity.setPassword(encodedPassword);

        userRepository.save(userEntity);

        var sms = getRandomNumberString();

        var smsOptional = confirmationSmsService.getSms(sms);
        smsOptional.ifPresent(confirmationSmsService::delete);

        var confirmationSms = new ConfirmationSmsEntity(
                sms,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(2),
                userEntity
        );
        confirmationSmsService.confirmationSms(confirmationSms);

        // TODO: an SMS code must be sent to the phone number

        return sms;
    }

    public UserEntity getUserByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(() -> new ItemNotFoundException("user not found"));
    }
    private String getRandomNumberString() {

        var rnd = new Random();
        int number = rnd.nextInt(99999);

        return String.format("%05d", number);
    }
}
