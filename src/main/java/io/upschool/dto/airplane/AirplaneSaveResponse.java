package io.upschool.dto.airplane;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirplaneSaveResponse {
    private Long id;
    private String registrationNumber;
    private int seatCapacity;
    private List<String> seatNumberList;
}
