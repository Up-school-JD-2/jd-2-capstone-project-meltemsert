package io.upschool.service;

import io.upschool.entity.Seat;
import io.upschool.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
   // private final AirplaneService airplaneService;

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

//    public SeatSaveResponse save(SeatSaveRequest request) {
//        Airplane airplaneByReference = airplaneService.getReferenceById(request.getAirplaneId());
//         var newSeat= Seat.builder()
//                 .seatNumber(request.getSeatNumber())
//                 .seatType(SeatType.valueOf(request.getSeatType()))
//                 .airplane(airplaneByReference)
//                 .build();
//         Seat savedSeat=seatRepository.save(newSeat);
//
//         return SeatSaveResponse.builder()
//                 .id(savedSeat.getId())
//                 .seatNumber(savedSeat.getSeatNumber())
//                 .registrationNumber(savedSeat.getAirplane().getRegistrationNumber())
//                 .build();
//   }

    public Seat getReferenceById(Long seatId) {
        return seatRepository.getReferenceById(seatId);
   }
}
