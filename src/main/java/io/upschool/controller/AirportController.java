package io.upschool.controller;

import io.upschool.dto.airport.AirportSaveRequest;
import io.upschool.dto.airport.AirportSaveResponse;
import io.upschool.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<List <AirportSaveResponse>> getAllAirport(){
        var airports= airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    @PostMapping
    public ResponseEntity<AirportSaveResponse> createAirport(@RequestBody AirportSaveRequest request){
       var response= airportService.save(request);
       return ResponseEntity.ok(response);
    }
}
