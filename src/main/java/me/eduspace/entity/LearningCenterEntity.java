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

    @OneToOne
    @JoinColumn(name = "attach_id")
    AttachEntity attach;
}