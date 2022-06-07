package me.eduspace.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.eduspace.entity.AttachEntity;
import me.eduspace.enums.Gender;
import me.eduspace.enums.GeneralStatus;
import me.eduspace.enums.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifedDate;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private LocalDate birthDate;
    private GeneralStatus status;
    private UserRole role;
    private Gender gender;
    private Boolean locked;
    private Boolean enabled;
    private AttachEntity attach;

    private String jwt;
}
