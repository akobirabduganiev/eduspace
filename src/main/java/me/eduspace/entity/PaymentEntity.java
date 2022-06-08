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

    @Column
    private Long amout;
    @Column
    private PaymentType type;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
}