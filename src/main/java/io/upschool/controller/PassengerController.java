package io.upschool.controller;

import io.upschool.dto.passenger.PassengerSaveRequest;
import io.upschool.dto.passenger.PassengerSaveResponse;
import io.upschool.entity.Passenger;
import io.upschool.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<Passenger>> getPassengers(){
       var passengers= passengerService.getAllPassengers();
       return ResponseEntity.ok(passengers);
    }

    @PostMapping
    public ResponseEntity<PassengerSaveResponse> createPassenger(@RequestBody PassengerSaveRequest request ){
       var response=  passengerService.save(request);
       return ResponseEntity.ok(response);
    }
}
