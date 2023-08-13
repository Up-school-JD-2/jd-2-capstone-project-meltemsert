package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.airlinecompany.AirlineCompanyRequest;
import io.upschool.dto.airlinecompany.AirlineCompanyResponse;
import io.upschool.service.AirlineCompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlinecompanies")
@RequiredArgsConstructor
public class AirlineCompanyController {
    private final AirlineCompanyService airlineCompanyService;

    @GetMapping
    public ResponseEntity<Object> getAirlineCompanies(){

        var airlineCompanies=airlineCompanyService.getAllAirlineCompanies();
        var response= BaseResponse.<List<AirlineCompanyResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airlineCompanies)
                .build();
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<Object> createAirlineCompany(@Valid @RequestBody AirlineCompanyRequest request){

        var airlineCompany=airlineCompanyService.save(request);
        var response= BaseResponse.<AirlineCompanyResponse>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airlineCompany)
                .build();
        return ResponseEntity.ok(response);
    }
}
