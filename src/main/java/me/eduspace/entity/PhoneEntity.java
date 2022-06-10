package me.eduspace.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "phone")
public class PhoneEntity extends BaseEntity {
//    id, phone, lc_id
    @Column
    private String phone;
    
    @Column(name = "learning_center_id", nullable = false)
    private Long learningCenterId;
    @ManyToOne
    @JoinColumn(name = "learning_center_id", insertable = false, updatable = false)
    private LearningCenterEntity learningCenter;
}