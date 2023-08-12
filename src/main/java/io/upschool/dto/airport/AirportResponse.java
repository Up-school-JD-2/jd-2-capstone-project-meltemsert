package io.upschool.dto.airport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportResponse {
    private Long id;
    private String name;
    private String iataCode;
    private String cityName;
}
