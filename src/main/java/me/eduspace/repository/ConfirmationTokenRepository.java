package me.eduspace.repository;


import me.eduspace.entity.ConfirmationTokenEntity;
import me.eduspace.enums.ConfirmationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, Long> {
    Optional<ConfirmationTokenEntity> findBySmsAndStatus(String sms, ConfirmationStatus status);
    Optional<ConfirmationTokenEntity> findByUserEntityIdAndStatus(Long userId, ConfirmationStatus status);

    @Transactional
    @Modifying
    @Query("update ConfirmationTokenEntity set confirmedAt=:confirmedAt where sms=:sms")
    void updateConfirmedAt(@Param("confirmedAt") LocalDateTime confirmedAt, @Param("sms") String sms);

    @Transactional
    @Modifying
    @Query(value = "update ConfirmationTokenEntity set status=:status where sms=:sms")
    void updateStatus(@Param("status") ConfirmationStatus status, @Param("sms") String sms);


}