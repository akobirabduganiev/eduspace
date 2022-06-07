package me.eduspace.dto.time;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
public class TimeRequestDTO {
    @NotNull
    private String startedTime;
    @NotNull
    private String endTime;
}
