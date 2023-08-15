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
public class FlightResponse {
    private Long flightId;
    private String routeName;
    private String flightNumber;
    private LocalDateTime departureDateAndTime;
    private LocalDateTime arrivalDateAndTime;
    private String airlineCompanyName;
    private Integer capacity;
    private Float price;
}
