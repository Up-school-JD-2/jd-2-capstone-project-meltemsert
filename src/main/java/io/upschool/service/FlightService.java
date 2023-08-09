package io.upschool.service;

import io.upschool.dto.flight.FlightSaveRequest;
import io.upschool.dto.flight.FlightSaveResponse;
import io.upschool.entity.AirlineCompany;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.exception.FlightAlreadySavedException;
import io.upschool.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final RouteService routeService;
    private final AirlineCompanyService airlineCompanyService;
    public List<FlightSaveResponse> getAllFlights(){
        return flightRepository.findAll().stream().map(flight -> FlightSaveResponse.builder()
                .flightId(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .routeCodeName(flight.getRoute().getRouteName())
                .departureDateAndTime(flight.getDepartureDateAndTime())
                .arrivalDateAndTime(flight.getArrivalDateAndTime())
                .airlineCompanyName(flight.getAirlineCompany().getName())
                .capacity(flight.getCapacity())
                .build()).collect(Collectors.toList());
    }

    public FlightSaveResponse save(FlightSaveRequest request) {
        checkIsFlightAlreadySaved(request);
        Route routeByReference=routeService.getReferenceById(request.getRouteId());
        AirlineCompany airlineCompanyByReference=
                airlineCompanyService.getReferenceById(request.getAirlineCompanyId());
        var newFlight= Flight.builder()
                .route(routeByReference)
                .departureDateAndTime(request.getDepartureDateAndTime())
                .arrivalDateAndTime(request.getArrivalDateAndTime())
                .airlineCompany(airlineCompanyByReference)
                .price(request.getPrice())
                .build();
        Flight savedFlight=flightRepository.save(newFlight);
        return FlightSaveResponse.builder()
                .flightId(savedFlight.getId())
                .routeCodeName(savedFlight.getRoute().getRouteName())
                .departureDateAndTime(savedFlight.getDepartureDateAndTime())
                .arrivalDateAndTime(savedFlight.getArrivalDateAndTime())
                .airlineCompanyName(savedFlight.getAirlineCompany().getName())
                .capacity(savedFlight.getCapacity())
                .price(savedFlight.getPrice())
                .build();
    }
    public void checkIsFlightAlreadySaved(FlightSaveRequest request){
        boolean flightByNumber=flightRepository.existsByName(request.getFlightNumber());
        if(flightByNumber){
           throw new FlightAlreadySavedException("Bu uçuş daha önce eklenmiş");
        }
    }

    public Flight getReferenceById(Long flightId) {
        return flightRepository.getReferenceById(flightId);
    }
}

