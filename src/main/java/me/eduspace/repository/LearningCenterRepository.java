package me.eduspace.repository;

import me.eduspace.entity.LearningCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningCenterRepository extends JpaRepository<LearningCenterEntity, Long> {
}