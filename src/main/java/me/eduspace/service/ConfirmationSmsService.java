package me.eduspace.service;


import lombok.AllArgsConstructor;
import me.eduspace.entity.ConfirmationSmsEntity;
import me.eduspace.enums.ConfirmationStatus;
import me.eduspace.repository.ConfirmationSmsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationSmsService {
    private final ConfirmationSmsRepository confirmationSmsRepository;

    public void confirmationSms(ConfirmationSmsEntity sms) {
        confirmationSmsRepository.save(sms);
    }

    public Optional<ConfirmationSmsEntity> getSms(String sms) {
        return confirmationSmsRepository.findBySmsAndStatus(sms, ConfirmationStatus.ACTIVE);
    }

    public void setConfirmedAt(String sms) {
        confirmationSmsRepository.updateConfirmedAt(LocalDateTime.now(), sms);
    }

    public void delete(ConfirmationSmsEntity sms) {
        confirmationSmsRepository.delete(sms);
    }

    public void updateStatus(String sms, ConfirmationStatus status){
        confirmationSmsRepository.updateStatus(status,sms);
    }

    public ConfirmationSmsEntity getByUserId(Long userId){
        return confirmationSmsRepository.findByUserEntityIdAndStatus(userId,ConfirmationStatus.TIME_OUT)
                .orElseThrow(()->new IllegalArgumentException("Item not found"));
    }

}
