package io.upschool.dto.creditcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardResponse {
    private Long id;
    private String cvv;
    private String cardNumber;
    private String expiredMonth;
    private String expiredYear;
    private String passengerNameSurname;
}
