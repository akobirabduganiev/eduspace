package me.eduspace.repository;

import me.eduspace.entity.UserEntity;
import me.eduspace.enums.Gender;
import me.eduspace.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update UserEntity u " +
            "set u.enabled = true, u.status = ?2 where u.email=?1")
    void enableUser(String email, UserStatus status);

    @Transactional
    @Modifying
    @Query("update UserEntity u set u.imageLink=?1 where u.email =?2")
    void setImageLink(String imageLink, String email);

    @Transactional
    @Modifying
    @Query("update UserEntity set lastModifiedDate=?1 where email=?2")
    void updateLastModifiedDate(LocalDateTime lastModifiedDate, String email);

    @Transactional
    @Modifying
    @Query("update UserEntity set name=:name, surname=:surname , phone=:phone ,birthDate=:birthDate , gender=:gender  where id=:id")
    int updateDetail(@Param("name") String name, @Param("surname") String surname, @Param("phone") String phone,
                      @Param("birthDate") LocalDate birthDate, @Param("gender") Gender gender, @Param("id") Long id);
}