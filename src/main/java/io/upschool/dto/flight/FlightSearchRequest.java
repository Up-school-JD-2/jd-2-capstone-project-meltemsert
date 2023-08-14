package io.upschool.dto.flight;

import io.upschool.dto.airlinecompany.AirlineCompanySearchRequest;
import io.upschool.dto.route.RouteSearchRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSearchRequest {
    private RouteSearchRequest route;
    private AirlineCompanySearchRequest airlineCompany;
}
