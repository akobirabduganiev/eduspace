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
    Optional<ConfirmationTokenEntity> findByTokenAndStatus(String token, ConfirmationStatus status);
    Optional<ConfirmationTokenEntity> findByUserEntityIdAndStatus(Long userId, ConfirmationStatus status);

    @Transactional
    @Modifying
    @Query("update ConfirmationTokenEntity set confirmedAt=:confirmedAt where token=:token")
    void updateConfirmedAt(@Param("confirmedAt") LocalDateTime confirmedAt, @Param("token") String token);

    @Transactional
    @Modifying
    @Query(value = "update ConfirmationTokenEntity set status=:status where token=:token")
    void updateStatus(@Param("status") ConfirmationStatus status, @Param("token") String token);


}