package io.upschool.service;

import io.upschool.dto.route.RouteSaveRequest;
import io.upschool.dto.route.RouteSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.exception.CityAlreadySavedException;
import io.upschool.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final AirportService airportService;

    public List<RouteSaveResponse> getAllRoutes() {

        return routeRepository.findAll().stream().map(route -> RouteSaveResponse.builder()
                .id(route.getId())
                .routeName(route.getRouteName())
                .departureAirportName(route.getDepartureAirport().getName())
                .arrivalAirportName(route.getArrivalAirport().getName())
                .flightTime(route.getFlightTime())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public RouteSaveResponse save(RouteSaveRequest request) {
        Airport airportByReference = airportService.getReferenceById(request.getDepartureAirportId());
        Airport airportByReference1 = airportService.getReferenceById(request.getArrivalAirportId());
        var newRoute = Route.builder()
                .routeName(request.getRouteName())
                .departureAirport(airportByReference)
                .arrivalAirport(airportByReference1)
                .flightTime(request.getFlightTime())
                .build();
        Route savedRoute = routeRepository.save(newRoute);

        return RouteSaveResponse.builder()
                .id(savedRoute.getId())
                .routeName(savedRoute.getRouteName())
                .departureAirportName(savedRoute.getDepartureAirport().getName())
                .arrivalAirportName(savedRoute.getArrivalAirport().getName())
                .flightTime(savedRoute.getFlightTime())
                .build();
    }

    private void checkIsRouteAlreadySaved(RouteSaveRequest request) {
        Route routeByName= routeRepository.findByRouteNameIgnoreCase(request.getRouteName());
        if (routeByName!=null) {
            throw new CityAlreadySavedException("Bu city daha önce eklenmiş");
        }
    }

    public Route getReferenceById(Long routeId) {
        return routeRepository.getReferenceById(routeId);
    }
}
