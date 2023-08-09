package io.upschool.controller;

import io.upschool.dto.route.RouteSaveRequest;
import io.upschool.dto.route.RouteSaveResponse;
import io.upschool.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<List<RouteSaveResponse>> getRoutes(){
       var routes= routeService.getAllRoutes();
       return ResponseEntity.ok(routes);
    }

    @PostMapping
    public ResponseEntity<RouteSaveResponse> createRoute(@RequestBody RouteSaveRequest request){
        var response=routeService.save(request);
        return ResponseEntity.ok(response);
    }
}
