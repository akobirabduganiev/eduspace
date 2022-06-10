package me.eduspace.entity;

import lombok.Getter;
import lombok.Setter;
import me.eduspace.enums.WeekDays;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "time_table")
public class TimeEntity extends BaseEntity {
//    ->id, start_date, end_datre, WEEK_DAYS, group_id

    @Column
    private LocalTime startedTime;

    @Column
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private WeekDays weekDays;


}