package me.eduspace.repository;

import me.eduspace.entity.LearningCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface LearningCenterRepository extends JpaRepository<LearningCenterEntity, Long> {
    Optional<LearningCenterEntity> findByIdAndIsDeleted(Long id, Boolean isDeleted);

    @Override
    @Transactional
    @Modifying
    @Query("update LearningCenterEntity set isDeleted=true where id=?1")
    void deleteById(Long id);

    @Transactional
    @Modifying
    @Query("update LearningCenterEntity set lastModifiedDate=?1 where id=?2")
    void updateLastModifiedDate(LocalDateTime lastModifiedDate, Long id);

}