package io.upschool.service;

import io.upschool.dto.flight.FlightRequest;
import io.upschool.dto.flight.FlightResponse;
import io.upschool.dto.flight.FlightSearchRequest;
import io.upschool.entity.AirlineCompany;
import io.upschool.entity.Airport;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.exception.FlightAlreadySavedException;
import io.upschool.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final RouteService routeService;
    private final AirlineCompanyService airlineCompanyService;

    public List<FlightResponse> getAllFlights() {
        return flightRepository.findAll().stream().map(flight -> entityToResponse(flight)).toList();
    }

    @Transactional
    public FlightResponse save(FlightRequest request) {
        checkIsFlightAlreadySaved(request);

        Flight flight = Flight.builder()
                .route(routeService.getReferenceById(request.getRouteId()))
                .flightNumber(request.getFlightNumber())
                .departureDateAndTime(request.getDepartureDateAndTime())
                .arrivalDateAndTime(request.getArrivalDateAndTime())
                .airlineCompany(airlineCompanyService.getReferenceById(request.getAirlineCompanyId()))
                .price(request.getPrice())
                .build();

        Flight savedFlight = flightRepository.save(flight);

        return entityToResponse(savedFlight);

    }

    @Transactional(readOnly = true)
    public Flight getReferenceById(Long flightId) {
        return flightRepository.getReferenceById(flightId);
    }

    public FlightResponse entityToResponse(Flight flight) {
        return FlightResponse.builder()
                .flightId(flight.getId())
                .routeName(flight.getRoute().getRouteName())
                .flightNumber(flight.getFlightNumber())
                .departureDateAndTime(flight.getDepartureDateAndTime())
                .arrivalDateAndTime(flight.getArrivalDateAndTime())
                .airlineCompanyName(flight.getAirlineCompany().getName())
                .capacity(flight.getCapacity())
                .price(flight.getPrice())
                .build();

    }

    private void checkIsFlightAlreadySaved(FlightRequest request) {
        boolean flightByNumber = flightRepository.existsByFlightNumber(request.getFlightNumber());
        if (flightByNumber) {
            throw new FlightAlreadySavedException("This flight has already been registered");
        }
    }

    public List<FlightResponse> search(FlightSearchRequest request) {
        var routeBuilder = Route.builder();
        var flightBuilder = Flight.builder();

        if (request.getRoute().getDeparture() != null) {
            Airport departureAirport = Airport.builder()
                    .name(request.getRoute().getDeparture().getName())
                    .build();
            routeBuilder.departureAirport(departureAirport);
        }
        if (request.getRoute().getArrival() != null) {
            Airport arrivalAirport = Airport.builder()
                    .name(request.getRoute().getArrival().getName())
                    .build();
            routeBuilder.arrivalAirport(arrivalAirport);
        }
        if (request.getRoute().getAirline() != null) {
            AirlineCompany airlineCompany = AirlineCompany.builder()
                    .name(request.getRoute().getAirline().getName())
                    .iataCode(request.getRoute().getAirline().getIataCode())
                    .build();
            routeBuilder.airlineCompany(airlineCompany);
        }
        if (request.getRoute().getRouteName() != null) {
            routeBuilder.routeName(request.getRoute().getRouteName());
        }
        Route route = routeBuilder.build();

        if (route != null) {
            flightBuilder.route(route);
        }

        if (request.getAirlineCompany().getName() != null) {
            AirlineCompany airlineCompany = AirlineCompany.builder()
                    .name(request.getAirlineCompany().getName())
                    .build();
            flightBuilder.airlineCompany(airlineCompany);
        }
        Flight flight = flightBuilder.build();

        Example<Flight> flightExample = Example.of(flight,
                ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        List<Flight> flights = flightRepository.findAll(flightExample);
        return flights.stream().map(f -> entityToResponse(f)).toList();
    }
}