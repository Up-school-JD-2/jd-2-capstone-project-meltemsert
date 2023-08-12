package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.city.CityResponse;
import io.upschool.dto.passenger.PassengerRequest;
import io.upschool.dto.passenger.PassengerResponse;
import io.upschool.entity.Passenger;
import io.upschool.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    @GetMapping
    public ResponseEntity<Object> getPassengers() {
        var passengers = passengerService.getAllPassengers();
        var response = BaseResponse.<List<PassengerResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .isSuccess(true)
                        .data(passengers)
                        .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createPassenger(@Valid @RequestBody PassengerRequest request) {
        var passenger = passengerService.save(request);
        var response = BaseResponse.<PassengerResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(passenger)
                .build();
        return ResponseEntity.ok(response);
    }
}
