package me.eduspace.repository;

import me.eduspace.entity.PaymentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    Page<PaymentEntity>findAllByUserId(Long userId, Pageable pageable);
}