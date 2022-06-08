package me.eduspace.dto.learningcenter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LearningCenterRegistrationRequestDTO {
    @NotBlank(message = "name required")
    @Size(min = 3, max = 255, message = "entered information must be more than 3 letters and less than 255 letters!")
    private String name;

    @NotBlank(message = "phone required")
    private String phone;

    @NotBlank(message = "description required")
    private String description;
}
