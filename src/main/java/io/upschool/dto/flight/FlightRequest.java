package io.upschool.dto.flight;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightRequest {
    @NotNull
    private Long routeId;
    @NotBlank
    private String flightNumber;
    @NotNull
    private LocalDateTime departureDateAndTime;
    @NotNull
    private LocalDateTime arrivalDateAndTime;
    @NotNull
    private Long airlineCompanyId;
    @NotNull
    private float price;
}
