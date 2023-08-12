package io.upschool.dto.route;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteRequest {
    @NotBlank
    private String routeName;

    @NotNull
    private Long airlineCompanyId;

    @NotNull
    private Long departureAirportId;
    @NotNull
    private Long arrivalAirportId;
    @NotNull
    private float flightTime;
}
