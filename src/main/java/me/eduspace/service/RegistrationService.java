package me.eduspace.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.dto.registration.RegistrationRequestDTO;
import me.eduspace.entity.ConfirmationTokenEntity;
import me.eduspace.entity.UserEntity;
import me.eduspace.enums.ConfirmationStatus;
import me.eduspace.enums.UserRole;
import me.eduspace.exceptions.AppBadRequestException;
import me.eduspace.exceptions.ItemAlreadyExistsException;
import me.eduspace.exceptions.ItemNotFoundException;
import me.eduspace.exceptions.TimeExpiredException;
import me.eduspace.util.email.EmailSender;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static me.eduspace.util.email.BuildEmail.buildEmail;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public String registerUser(RegistrationRequestDTO request) {

        try {
            LocalDate date = LocalDate.parse(request.getBirthDate(),
                    DateTimeFormatter.ofPattern("d/MM/yyyy"));

            String token = userService.signUpUser(
                    new UserEntity(
                            request.getName(),
                            request.getSurname(),
                            request.getEmail(),
                            request.getPassword(),
                            UserRole.ROLE_USER,
                            date,
                            request.getGender()
                    )
            );

            String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;

            var thread = new Thread(() -> emailSender.send(
                    request.getEmail(),
                    buildEmail(request.getName(), link)));
            thread.start();

            return "Please confirm this token: " + token;
        } catch (DateTimeException e) {
            log.warn("incorrect birthdate");
            throw new AppBadRequestException("date of birth entered incorrectly!");
        }
    }


    public String confirmToken(String token) {
        var confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new ItemNotFoundException("token not found"));

        if (confirmationToken.getConfirmedAt() != null)
            throw new ItemAlreadyExistsException("email already confirmed");

        var expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            confirmationTokenService.updateStatus(token, ConfirmationStatus.TIME_OUT);
            throw new TimeExpiredException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUserEntity().getEmail());

        return "confirmed successfully!";
    }

    public String againSmsCode(Long userId) {
        var confirmationToken = confirmationTokenService.getByUserId(userId);

        var token = UUID.randomUUID().toString();

        confirmationTokenService.delete(confirmationToken);

        confirmationTokenService.confirmationSms(
                new ConfirmationTokenEntity(
                        token,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(2),
                        ConfirmationStatus.ACTIVE,
                        confirmationToken.getUserEntity()
                )
        );

        return "Please confirm this code: " + token;

    }
}
