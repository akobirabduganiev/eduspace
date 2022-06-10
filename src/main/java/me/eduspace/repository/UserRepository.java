package me.eduspace.repository;

import me.eduspace.entity.UserEntity;
import me.eduspace.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update UserEntity u " +
            "set u.enabled = true, u.status = ?2 where u.email=?1")
    void enableUser(String email, UserStatus status);
}