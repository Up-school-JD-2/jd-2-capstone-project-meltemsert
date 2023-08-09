package io.upschool.dto.ticket;

import io.upschool.entity.Flight;
import io.upschool.entity.Passenger;
import io.upschool.entity.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveRequest {
    private String ticketNumber;
    private Long passengerId;
    private Long flightId;
    private LocalDateTime purchaseDateAndTime;
    private String ticketStatus;
}
