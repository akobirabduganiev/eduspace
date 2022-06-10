package me.eduspace.service;


import lombok.AllArgsConstructor;
import me.eduspace.entity.ConfirmationTokenEntity;
import me.eduspace.enums.ConfirmationStatus;
import me.eduspace.repository.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void confirmationSms(ConfirmationTokenEntity token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationTokenEntity> getToken(String token) {
        return confirmationTokenRepository.findBySmsAndStatus(token, ConfirmationStatus.ACTIVE);
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(LocalDateTime.now(), token);
    }

    public void delete(ConfirmationTokenEntity token) {
        confirmationTokenRepository.delete(token);
    }

    public void updateStatus(String token, ConfirmationStatus status){
        confirmationTokenRepository.updateStatus(status,token);
    }

    public ConfirmationTokenEntity getByUserId(Long userId){
        return confirmationTokenRepository.findByUserEntityIdAndStatus(userId,ConfirmationStatus.TIME_OUT)
                .orElseThrow(()->new IllegalArgumentException("Item not found"));
    }

}
