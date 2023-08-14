package io.upschool.dto.city;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityRequest {

    @Size(min = 3, max = 20,
            message = "The city name must be {min} and {max} characters long!")
    private String name;

}
