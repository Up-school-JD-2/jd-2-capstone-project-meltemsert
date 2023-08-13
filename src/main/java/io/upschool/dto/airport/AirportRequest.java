package io.upschool.dto.airport;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class AirportRequest {

    @Size(min = 13, max = 50,
            message = "The airport name must be {min} and {max} characters long!")
    private String name;


    @Size(min = 3, max = 3,
            message = "The airport iata code must be between {min} and {max} characters long!")
    private String iataCode;

    @NotNull (message = "Make sure that the city id is correctly and not blank!")
    private Long cityId;

}
