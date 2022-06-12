package me.eduspace.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "learning_center")
public class LearningCenterEntity extends BaseEntity {
//    id, desc, name, attach_id
    @Column(columnDefinition = "text")
    private String description;
    @Column
    private String name;
    @Column
    private String imageLink;
}