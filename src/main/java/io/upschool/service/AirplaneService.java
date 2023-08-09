package io.upschool.service;

import io.upschool.dto.airplane.AirplaneSaveRequest;
import io.upschool.dto.airplane.AirplaneSaveResponse;
import io.upschool.entity.Airplane;
import io.upschool.entity.Seat;
import io.upschool.entity.SeatType;
import io.upschool.repository.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final SeatService seatService;

    public List<Airplane> getAllAirplane() {
        return airplaneRepository.findAll();
    }

//    public AirplaneSaveResponse save(AirplaneSaveRequest request) {
//        Airplane requestEntity=Airplane.builder()
//                .registrationNumber(request.getRegistrationNumber())
//                .seatCapacity(request.getSeatCapacity())
//                .build();
//
//        Airplane savedAirplane=airplaneRepository.save(requestEntity);
//
//
//        List<Seat> seats= request.getSeats().stream().map(seatRequest -> {
//             return Seat.builder()
//                    .seatType(SeatType.valueOf(seatRequest.getSeatType()))
//                    .seatNumber(seatRequest.getSeatNumber())
//                    .airplane(savedAirplane)
//                    .build();
//        }).toList();
//
//        return AirplaneSaveResponse.builder()
//                .id(savedAirplane.getId())
//                .registrationNumber(savedAirplane.getRegistrationNumber())
//                .seatCapacity(savedAirplane.getSeatCapacity())
//                .seatNumberList(savedAirplane.getSeats().)
//    }

    public Airplane getReferenceById(Long airplaneId) {

        return airplaneRepository.getReferenceById(airplaneId);
    }


}
