package io.upschool.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class TicketResponse {
    private Long id;
    private String ticketNumber;
    private LocalDateTime purchaseDateAndTime;
    private String ticketStatus;
    private Long passengerId;
    private String nameSurname;
    private Long flightId;
    private String routeName;

}
