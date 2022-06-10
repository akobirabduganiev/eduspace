package me.eduspace.entity;

import lombok.Getter;
import lombok.Setter;
import me.eduspace.enums.GroupStatus;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "group")
public class GroupEntity extends BaseEntity {
//    -> id, name, GROUP_STATUS, cl_id, course_id
    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private GroupStatus status;

    @Column(name = "leraning_center_id", nullable = false)
    private Long leraningCenterId;
    @ManyToOne
    @JoinColumn(name = "leraning_center_id", insertable = false, updatable = false)
    private LearningCenterEntity learningCenter;

    @Column(name = "cource_id", nullable = false)
    private Long courceId;
    @ManyToOne
    @JoinColumn(name = "cource_id", insertable = false, updatable = false)
    private CourseEntity course;
}