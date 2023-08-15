package io.upschool.dto.route;

import io.upschool.dto.airport.AirportSearchRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSearchRequest {
    private String routeName;
    private AirportSearchRequest departure;
    private AirportSearchRequest arrival;
}


