package io.upschool.dto.airlinecompany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineCompanySearchRequest {

    private String name;

    private String iataCode;
}
