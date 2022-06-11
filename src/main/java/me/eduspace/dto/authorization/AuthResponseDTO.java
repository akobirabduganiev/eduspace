package me.eduspace.dto.authorization;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String jwt;
}
