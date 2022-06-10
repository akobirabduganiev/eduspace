package me.eduspace.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.eduspace.enums.UserRole;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDTO {
    @NotNull(message = "name request!")
    private String name;
    @NotNull(message = "surname request!")
    private String surname;
    private String phone;
    @NotNull(message = "password request!")
    private String password;
    private LocalDate birthDate;
    private UserRole role;
}
