package io.upschool.dto.route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveResponse {
    private Long id;
    private String routeName;
    private String departureAirportName;
    private String arrivalAirportName;
    private float flightTime;
}
