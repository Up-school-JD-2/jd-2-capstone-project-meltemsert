package io.upschool.dto.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String ticketNumber;
    @NotNull
    private Long passengerId;
    @NotNull
    private Long flightId;
    @NotNull
    private LocalDateTime purchaseDateAndTime;
    @NotBlank
    private String ticketStatus;
}
