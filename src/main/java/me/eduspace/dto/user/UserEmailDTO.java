package me.eduspace.dto.user;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserEmailDTO {
    @NonNull
    private String email;
}
