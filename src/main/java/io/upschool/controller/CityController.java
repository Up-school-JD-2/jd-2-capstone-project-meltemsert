package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.city.CityRequest;
import io.upschool.dto.city.CityResponse;
import io.upschool.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<Object> getCities() {
        List<CityResponse> cities = cityService.getAllCities();

        BaseResponse response = BaseResponse.<List<CityResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(cities)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createCity(@Valid @RequestBody CityRequest request) {
        CityResponse city = cityService.save(request);

        BaseResponse response = BaseResponse.<CityResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(city)
                .build();
        return ResponseEntity.ok(response);
    }
}

