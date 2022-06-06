package me.eduspace.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "learning_center")
public class LearningCenterEntity extends BaseEntity {
    @Column(columnDefinition = "text")
    private String description;
    @Column
    private String name;
    @Column
    private String phone;

    @Column(name = "user_id")
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;
}