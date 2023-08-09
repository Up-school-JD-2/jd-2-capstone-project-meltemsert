package io.upschool.dto.flight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSaveResponse {
    private Long flightId;
    private String flightNumber;
    private String routeCodeName;
    private LocalDateTime departureDateAndTime;
    private LocalDateTime arrivalDateAndTime;
    private String airlineCompanyName;
    private int capacity;
    private float price;
}
