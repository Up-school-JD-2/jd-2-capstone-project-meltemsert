package io.upschool.dto.ticket;

import io.upschool.dto.passenger.PassengerResponse;
import io.upschool.dto.payment.PaymentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {

    private String ticketNumber;
    private LocalDateTime purchaseDateAndTime;
    private String ticketStatus;
    private PassengerResponse passengerResponse;
    private PaymentResponse paymentResponse;
    private String routeName;

}
