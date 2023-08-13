package io.upschool.dto.flight;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Make sure that route id is correctly and not blank!")
    private Long routeId;

    @Size(min = 5, max = 10,
            message = "The flight number must be {min} and {max} characters long!")
    private String flightNumber;


    @NotNull(message = "Make sure that the departure date and time is exactly 19 characters long!")
    private LocalDateTime departureDateAndTime;


    @NotNull(message = "Make sure that the arrival date and time is exactly 19 characters long!")
    private LocalDateTime arrivalDateAndTime;

    @NotNull(message = "Make sure that the airline company id is correctly and not blank!")
    private Long airlineCompanyId;

    @NotNull(message = "Make sure that the price is correctly and not blank!")
    private float price;
}
