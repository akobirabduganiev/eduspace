package me.eduspace.entity;

import lombok.Getter;
import lombok.Setter;
import me.eduspace.enums.PaymentType;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class PaymentEntity extends BaseEntity {
//-> id, amount, PAYMENT_TYPE, started_date, end_date, user_id, nmadir_id
    @Column
    private Long amount;
    @Column
    private PaymentType type;
    @Column
    private LocalDate startDate=LocalDate.now();
    @Column
    private LocalDate endDate=LocalDate.now().plusMonths(1);
    @Column
    private Long itemId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;
}