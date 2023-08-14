package io.upschool.dto.route;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteRequest {

    @Size (min = 10, max = 70,
            message = "The route name must be {min} and {max} characters long!")
    private String routeName;

    @NotNull (message = "Make sure that the airline company id correctly and not blank!")
    private Long airlineCompanyId;

    @NotNull (message = "Make sure that the departure airport id correctly and not blank!")
    private Long departureAirportId;

    @NotNull (message = "Make sure that the arrival airport id correctly and not blank!")
    private Long arrivalAirportId;

    @NotNull (message = "Make sure that the flight time correctly and not blank!")
    private float flightTime;
}
