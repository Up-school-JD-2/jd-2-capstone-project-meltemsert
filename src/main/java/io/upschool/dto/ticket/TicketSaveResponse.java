package io.upschool.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class TicketSaveResponse {
    private Long id;
    private String ticketNumber;
    private Long passengerId;
    private Long flightId;
    private LocalDateTime purchaseDateAndTime;
    private String ticketStatus;
}
