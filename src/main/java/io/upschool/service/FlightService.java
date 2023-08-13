package io.upschool.service;

import io.upschool.dto.flight.FlightRequest;
import io.upschool.dto.flight.FlightResponse;
import io.upschool.entity.Flight;
import io.upschool.exception.FlightAlreadySavedException;
import io.upschool.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final RouteService routeService;
    private final AirlineCompanyService airlineCompanyService;
    public List<FlightResponse> getAllFlights(){
        return flightRepository.findAll().stream().map(flight -> FlightResponse.builder()
                .flightId(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .routeName(flight.getRoute().getRouteName())
                .departureDateAndTime(flight.getDepartureDateAndTime())
                .arrivalDateAndTime(flight.getArrivalDateAndTime())
                .airlineCompanyName(flight.getAirlineCompany().getName())
                .capacity(flight.getCapacity())
                .price(flight.getPrice())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public FlightResponse save(FlightRequest request) {

        checkIsFlightAlreadySaved(request);
        Flight flightResponse = buildFlightAndSave(request);

        return FlightResponse.builder()
                .flightId(flightResponse.getId())
                .routeName(flightResponse.getRoute().getRouteName())
                .flightNumber(flightResponse.getFlightNumber())
                .departureDateAndTime(flightResponse.getDepartureDateAndTime())
                .arrivalDateAndTime(flightResponse.getArrivalDateAndTime())
                .airlineCompanyName(flightResponse.getAirlineCompany().getName())
                .capacity(flightResponse.getCapacity())
                .price(flightResponse.getPrice())
                .build();
    }

    @Transactional(readOnly = true)
    public Flight getReferenceById(Long flightId) {
        return flightRepository.getReferenceById(flightId);
    }

    private Flight buildFlightAndSave(FlightRequest request) {

        Flight flight = Flight.builder()
                .route(routeService.getReferenceById(request.getRouteId()))
                .flightNumber(request.getFlightNumber())
                .departureDateAndTime(request.getDepartureDateAndTime())
                .arrivalDateAndTime(request.getArrivalDateAndTime())
                .airlineCompany(airlineCompanyService.getReferenceById(request.getAirlineCompanyId()))
                .price(request.getPrice())
                .build();
        return flightRepository.save(flight);
    }

    private void checkIsFlightAlreadySaved(FlightRequest request){
        boolean flightByNumber=flightRepository.existsByFlightNumber(request.getFlightNumber());
        if(flightByNumber){
            throw new FlightAlreadySavedException("This flight has already been registered");
        }
    }
}

