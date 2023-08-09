package io.upschool.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatSaveRequest {
    private String seatNumber;
    private String seatType;
    private Long airplaneId;
}
