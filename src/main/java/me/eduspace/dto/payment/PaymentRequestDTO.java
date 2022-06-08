package me.eduspace.dto.payment;

import lombok.Data;
import me.eduspace.enums.PaymentType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class PaymentRequestDTO {
    @NotNull(message = "amount request!")
    @Positive(message = "amount positive")
    private Long amount;
    @NotNull(message = "type request!")
    private PaymentType type;
}
