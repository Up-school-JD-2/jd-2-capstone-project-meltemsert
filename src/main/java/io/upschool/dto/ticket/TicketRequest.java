package io.upschool.dto.ticket;

import io.upschool.dto.passenger.PassengerRequest;
import io.upschool.dto.payment.PaymentRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketRequest {

    @Valid
    @NotNull(message = "Make sure that the passenger information is correctly and not blank!")
    private PassengerRequest passengerRequest;

    @NotNull(message = "Make sure that the flight id is correctly and not blank!")
    private Long flightId;

    @Valid
    @NotNull(message = "Make sure that the  payment information is correctly and not blank!")
    private PaymentRequest paymentRequest;

    @NotBlank(message = "Make sure that the ticket status is correctly and not blank!")
    private String ticketStatus;

}
