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
public class FlightSaveRequest {
    private Long routeId;
    private String flightNumber;
    private LocalDateTime departureDateAndTime;
    private LocalDateTime arrivalDateAndTime;
    private Long airlineCompanyId;
    private float price;
}
