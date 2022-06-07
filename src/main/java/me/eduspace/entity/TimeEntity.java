package me.eduspace.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "time_table")
public class TimeEntity extends BaseEntity {
    @Column
    private LocalTime startedTime;
    @Column
    private LocalTime endTime;
}