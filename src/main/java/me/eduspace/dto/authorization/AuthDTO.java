package me.eduspace.dto.authorization;

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
public class AuthDTO {
    @NotBlank(message = "phone required!")
    @Size(min = 3, max = 255, message = "entered information must be more than 3 letters and less than 255 letters!")
    private String phone;

    @NotBlank(message = "password required!")
    @Size(min = 3, max = 255, message = "entered information must be more than 3 letters and less than 255 letters!")
    private String password;
}
