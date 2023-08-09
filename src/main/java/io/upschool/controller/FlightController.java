package io.upschool.controller;

import io.upschool.dto.flight.FlightSaveRequest;
import io.upschool.dto.flight.FlightSaveResponse;
import io.upschool.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightSercive;

    @GetMapping
    public ResponseEntity<List<FlightSaveResponse>> getFlights(){
       var response= flightSercive.getAllFlights();
       return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<FlightSaveResponse> createFlight(@RequestBody FlightSaveRequest request){
       var response= flightSercive.save(request);
       return ResponseEntity.ok(response);
    }
}
