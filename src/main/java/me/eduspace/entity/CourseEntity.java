package me.eduspace.entity;

import lombok.Getter;
import lombok.Setter;
import me.eduspace.enums.CourseStatus;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "course_entity")
public class CourseEntity extends BaseEntity {
//-> id, name, duration, price, COURCE_STATUS, lc_id

    @Column(unique = true)
    private String name;

    @Column
    private Integer duration;

    @Column
    private Long price;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @Column(name = "leraning_center_id", nullable = false)
    private Long leraningCenterId;
    @ManyToOne
    @JoinColumn(name = "leraning_center_id", insertable = false, updatable = false)
    private LearningCenterEntity learningCenter;

}