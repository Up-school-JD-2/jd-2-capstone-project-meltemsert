package io.upschool.dto.airlinecompany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineCompanyResponse {
    private Long id;
    private String name;
    private String iataCode;
}
