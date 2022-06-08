package me.eduspace.dto.payment;

import lombok.Builder;
import lombok.Data;
import me.eduspace.dto.user.UserResponseDTO;
import me.eduspace.enums.PaymentType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private Long amount;
    private PaymentType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdDate;
}
