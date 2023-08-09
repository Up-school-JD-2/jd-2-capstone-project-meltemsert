package io.upschool.controller;

import io.upschool.dto.airplane.AirplaneSaveRequest;
import io.upschool.dto.airplane.AirplaneSaveResponse;
import io.upschool.entity.Airplane;
import io.upschool.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airplanes")
@RequiredArgsConstructor
public class AirplaneController {
    private final AirplaneService airplaneService;

    @GetMapping
    public ResponseEntity<List<Airplane>> getAirplane(){
        return null;
    }


//    public ResponseEntity<AirplaneSaveResponse> createAirplane(@RequestBody AirplaneSaveRequest request){
//        AirplaneSaveResponse response=airplaneService.save(request);
//        return null;
//    }
}
