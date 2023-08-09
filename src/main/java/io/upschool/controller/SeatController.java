package io.upschool.controller;

import io.upschool.dto.seat.SeatSaveRequest;
import io.upschool.dto.seat.SeatSaveResponse;
import io.upschool.entity.Seat;
import io.upschool.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @GetMapping
    public ResponseEntity<List<Seat>> getSeats(){
        var response=seatService.getAllSeats();
        return ResponseEntity.ok(response);
    }

//    @PostMapping
//    public ResponseEntity<SeatSaveResponse> createSeat(@RequestBody SeatSaveRequest request){
//        var response=seatService.save(request);
//        return ResponseEntity.ok(response);
//    }
}
