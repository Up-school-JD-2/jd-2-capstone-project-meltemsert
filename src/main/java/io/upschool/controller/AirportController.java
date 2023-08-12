package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.airlinecompany.AirlineCompanyResponse;
import io.upschool.dto.airport.AirportRequest;
import io.upschool.dto.airport.AirportResponse;
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

        var airports=airportService.getAllAirports();
        var response= BaseResponse.<List<AirportResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airports)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createAirport(@Valid @RequestBody AirportRequest request) {

        var airport = airportService.save(request);
        var response = BaseResponse.<AirportResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airport)
                .build();
        return ResponseEntity.ok(response);
    }
}

