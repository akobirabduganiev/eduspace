package me.eduspace.dto.user;

import lombok.Data;
import me.eduspace.enums.UserStatus;

import javax.validation.constraints.NotNull;

@Data
public class UserStatusDTO {
    @NotNull(message = "userId request!")
    private Long userId;
    @NotNull(message = "status request!")
    private UserStatus status;
}
