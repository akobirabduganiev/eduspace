package me.eduspace.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.entity.ConfirmationSmsEntity;
import me.eduspace.entity.UserEntity;
import me.eduspace.enums.ConfirmationStatus;
import me.eduspace.enums.GeneralStatus;
import me.eduspace.exceptions.ItemAlreadyExistsException;
import me.eduspace.exceptions.ItemNotFoundException;
import me.eduspace.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationSmsService confirmationSmsService;

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
                LocalDateTime.now().plusMinutes(0),
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

    public UserEntity getUserByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(() -> new ItemNotFoundException("user not found"));
    }

    public UserEntity getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new ItemNotFoundException("User not found"));
    }

    public String getRandomNumberString() {

        var rnd = new Random();
        int number = rnd.nextInt(99999);

        return String.format("%05d", number);
    }
}
