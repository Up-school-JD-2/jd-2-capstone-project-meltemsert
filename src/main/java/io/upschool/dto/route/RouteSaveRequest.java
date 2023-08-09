package io.upschool.dto.route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveRequest {
    private String routeName;
    private Long departureAirportId;
    private Long arrivalAirportId;
    private float flightTime;
}
