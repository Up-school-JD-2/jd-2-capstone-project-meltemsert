package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.flight.FlightRequest;
import io.upschool.dto.flight.FlightResponse;
import io.upschool.dto.flight.FlightSearchRequest;
import io.upschool.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<Object> getFlights() {
        List<FlightResponse> flights = flightService.getAllFlights();

        BaseResponse response = BaseResponse.<List<FlightResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(flights)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Object> searchFlight(@RequestBody FlightSearchRequest request) {
        List<FlightResponse> flights = flightService.search(request);

        BaseResponse response = BaseResponse.<List<FlightResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(flights)
                .build();
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<Object> createFlight(@Valid @RequestBody FlightRequest request) {
        FlightResponse flight = flightService.save(request);

        BaseResponse response = BaseResponse.<FlightResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flight)
                .build();
        return ResponseEntity.ok(response);
    }
}
