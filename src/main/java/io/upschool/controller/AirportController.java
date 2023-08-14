package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.airport.AirportRequest;
import io.upschool.dto.airport.AirportResponse;
import io.upschool.dto.airport.AirportSearchRequest;
import io.upschool.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;
    @GetMapping
    public ResponseEntity<Object> getAirports() {
        List<AirportResponse> airports = airportService.getAllAirports();

        BaseResponse response = BaseResponse.<List<AirportResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airports)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchAirport(AirportSearchRequest request) {
        List<AirportResponse> airports = airportService.search(request);

        BaseResponse response = BaseResponse.<List<AirportResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airports)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<Object> createAirport(@Valid @RequestBody AirportRequest request) {
        AirportResponse airport = airportService.save(request);

        BaseResponse response = BaseResponse.<AirportResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airport)
                .build();
        return ResponseEntity.ok(response);
    }
}

