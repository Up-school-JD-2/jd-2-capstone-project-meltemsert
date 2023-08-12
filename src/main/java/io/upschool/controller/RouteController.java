package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.flight.FlightResponse;
import io.upschool.dto.route.RouteRequest;
import io.upschool.dto.route.RouteResponse;
import io.upschool.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<Object> getRoutes(){
       var routes= routeService.getAllRoutes();
        var response= BaseResponse.<List<RouteResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(routes)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createRoute(@Valid @RequestBody RouteRequest request){
        var route=routeService.save(request);
        var response=BaseResponse.<RouteResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(route)
                .build();
        return ResponseEntity.ok(response);
    }
}
