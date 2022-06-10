package me.eduspace.entity;

import lombok.Getter;
import lombok.Setter;
import me.eduspace.enums.Permission;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_learning_center")
public class UserLearningCenterEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private Permission permission;

    @Column(name = "learning_center_id", nullable = false)
    private Long learningCenterId;
    @ManyToOne
    @JoinColumn(name = "learning_center_id", insertable = false, updatable = false)
    private LearningCenterEntity learningCenter;

    @Column(name = "user_id", nullable = false)
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;
}