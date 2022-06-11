package me.eduspace.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.eduspace.enums.Gender;
import me.eduspace.enums.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private LocalDate birthDate;
    private Gender gender;
    private String email;
    private String password;
    private UserRole role;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
