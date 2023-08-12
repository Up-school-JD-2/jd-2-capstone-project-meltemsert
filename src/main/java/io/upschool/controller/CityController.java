package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.city.CityRequest;
import io.upschool.dto.city.CityResponse;
import io.upschool.entity.City;
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
        var cities = cityService.getAllCities();
        var response= BaseResponse.<List<CityResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .isSuccess(true)
                        .data(cities)
                        .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id){
      City city= cityService.getCityById(id);
      return ResponseEntity.ok(city);
    }

    @PostMapping
    public ResponseEntity<Object> createCity(@Valid @RequestBody CityRequest request){
       var city= cityService.save(request);
       var response=BaseResponse.<CityResponse>builder()
               .status(HttpStatus.CREATED.value())
               .isSuccess(true)
               .data(city)
               .build();
       return ResponseEntity.ok(response);
    }


}

