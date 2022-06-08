package me.eduspace.dto.learningcenter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.eduspace.entity.AttachEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LearningCenterDTO {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String description;
    private String name;
    private String phone;
}
