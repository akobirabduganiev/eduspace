package me.eduspace.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.eduspace.enums.Gender;
import me.eduspace.enums.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private LocalDate birthDate;
    private UserRole role;
    private Gender gender;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String jwt;

    public UserResponseDTO(Long id, String name, String surname, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }
}
