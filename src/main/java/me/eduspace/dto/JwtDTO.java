package me.eduspace.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtDTO {
    private Long id;
    private String name;

    public JwtDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
