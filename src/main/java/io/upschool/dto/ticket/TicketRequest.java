package io.upschool.dto.ticket;

import io.upschool.dto.passenger.PassengerRequest;
import io.upschool.dto.payment.PaymentRequest;
import io.upschool.entity.Payment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketRequest {

    @NotNull(message = "Make sure that the passenger id is correctly and not blank!")
    private PassengerRequest passengerRequest;

    @NotNull(message = "Make sure that the flight id is correctly and not blank!")
    private Long flightId;

    private PaymentRequest paymentRequest;

    @NotBlank(message = "Make sure that the ticket status is correctly and not blank!")
    private String ticketStatus;

}
