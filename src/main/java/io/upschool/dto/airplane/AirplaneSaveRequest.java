package io.upschool.dto.airplane;

import io.upschool.dto.seat.SeatSaveRequest;
import io.upschool.entity.Seat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirplaneSaveRequest {
    @NotBlank
    private String registrationNumber;
    @NotNull
    private int seatCapacity;

    private List<SeatSaveRequest> seats;
}
