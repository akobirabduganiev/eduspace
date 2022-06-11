package me.eduspace.dto.user;

import lombok.Data;
import me.eduspace.enums.Gender;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserDetailDTO {
    @NotNull(message = "name request!")
    private String name;
    @NotNull(message = "surname request!")
    private String surname;
    @NotNull(message = "phone request!")
    private String phone;
    @NotNull(message = "birthDate request!")
    private LocalDate birthDate;
    @NotNull(message = "gender request!")
    private Gender gender;
}
