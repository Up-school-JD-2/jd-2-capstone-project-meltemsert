package io.upschool.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatSaveResponse {
    private Long id;
    private String seatNumber;
    private String seatType;
    private String registrationNumber;

}
