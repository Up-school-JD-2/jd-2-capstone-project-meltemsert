package io.upschool.controller;

import io.upschool.dto.airlinecompany.AirlineCompanySaveRequest;
import io.upschool.dto.airlinecompany.AirlineCompanySaveResponse;
import io.upschool.service.AirlineCompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlinecompanies")
@RequiredArgsConstructor
public class AirlineCompanyController {
    private final AirlineCompanyService airlineCompanyService;

    @GetMapping
    public ResponseEntity<List<AirlineCompanySaveResponse>> getAirlineCompany(){
        var response=airlineCompanyService.getAllAirlineCompanies();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AirlineCompanySaveResponse> create(@Valid @RequestBody AirlineCompanySaveRequest request){
        var response=airlineCompanyService.save(request);
        return ResponseEntity.ok(response);
    }
}
