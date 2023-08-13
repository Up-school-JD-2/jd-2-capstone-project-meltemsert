package io.upschool.dto.airlinecompany;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineCompanyRequest {

    @Size(min = 5, max = 30,
            message = "The airline company name must be {min} and {max} characters long!")
    private String name;


    @Size(min = 3, max = 3,
            message = "The airline iata code must be between {min} and {max} characters long!")
    private String iataCode;
}
