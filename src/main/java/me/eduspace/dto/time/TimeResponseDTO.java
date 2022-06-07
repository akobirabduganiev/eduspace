package me.eduspace.dto.time;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TimeResponseDTO  {
    private Long id;
    private LocalTime startedTime;
    private LocalTime endTime;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifedDate;
}
