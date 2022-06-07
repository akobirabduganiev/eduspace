package me.eduspace.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.dto.registration.RegistrationRequestDTO;
import me.eduspace.entity.UserEntity;
import me.eduspace.enums.UserRole;
import me.eduspace.exceptions.AppBadRequestException;
import me.eduspace.exceptions.ItemAlreadyExistsException;
import me.eduspace.exceptions.ItemNotFoundException;
import me.eduspace.exceptions.TimeExpiredException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    private final UserService userService;
    private final ConfirmationSmsService confirmationSmsService;

    public String registerUser(RegistrationRequestDTO request) {

        try {
            LocalDate date = LocalDate.parse(request.getBirthDate(),
                    DateTimeFormatter.ofPattern("d/MM/yyyy"));

            String smsCode = userService.signUpUser(
                    new UserEntity(
                            request.getName(),
                            request.getSurname(),
                            request.getPhone(),
                            request.getPassword(),
                            UserRole.ROLE_USER,
                            date
                    )
            );
            return "Please confirm this code: " + smsCode;
        } catch (DateTimeException e) {
            log.warn("incorrect birthdate");
            throw new AppBadRequestException("date of birth entered incorrectly!");
        }
    }

    @Transactional
    public String confirmSms(String sms) {
        var confirmationSms = confirmationSmsService
                .getSms(sms)
                .orElseThrow(() ->
                        new ItemNotFoundException("sms not found"));

        if (confirmationSms.getConfirmedAt() != null)
            throw new ItemAlreadyExistsException("phone already confirmed");

        var expiredAt = confirmationSms.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now()))
            throw new TimeExpiredException("sms expired");

        confirmationSmsService.setConfirmedAt(sms);
        userService.enableUser(
                confirmationSms.getUserEntity().getPhone());


        confirmationSmsService.delete(confirmationSms);

        return "confirmed successfully!";
    }
}
