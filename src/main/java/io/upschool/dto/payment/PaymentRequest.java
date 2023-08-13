package io.upschool.dto.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    @Pattern(regexp = "(\\d{4}[- ,]?\\d{4}[- ,]?\\d{4}[- ,]?\\d{4})",
            message = "Make sure that the credit card number is exactly 16 characters long!")
    private String creditCardNumber;
}
