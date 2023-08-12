package io.upschool.dto.creditcard;

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
public class CreditCardRequest {
    @NotBlank
    @Pattern(regexp = "\\d{3}" , message = "Geçerli bir cvv numarası giriniz.")
    private String cvv;
    @NotBlank
    @Pattern(regexp = "(\\d{4}[- ,]?\\d{4}[- ,]?\\d{4}[- ,]?\\d{4})" , message = "Geçerli bir kredi kartı numarası giriniz.")
    private String cardNumber;

    @NotBlank
    @Pattern(regexp = "(\\d{2})" , message = "Geçerli bir ay giriniz ." )
    private String expiredMonth;

    @NotBlank
    @Pattern(regexp = "(\\d{4})" , message = "Geçerli bir yıl giriniz ." )
    private String expiredYear;

    @NotNull
    private Long passengerId;

}
