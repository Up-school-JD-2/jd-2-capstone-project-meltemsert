package io.upschool.service;

import io.upschool.dto.route.RouteRequest;
import io.upschool.dto.route.RouteResponse;
import io.upschool.entity.Route;
import io.upschool.exception.RouteAlreadySavedException;
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
    private final AirlineCompanyService airlineCompanyService;

    public List<RouteResponse> getAllRoutes() {

        return routeRepository.findAll().stream().map(route -> RouteResponse.builder()
                .id(route.getId())
                .routeName(route.getRouteName())
                .airlineCompanyName(route.getAirlineCompany().getName())
                .departureAirportName(route.getDepartureAirport().getName())
                .arrivalAirportName(route.getArrivalAirport().getName())
                .flightTime(route.getFlightTime())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public RouteResponse save(RouteRequest request) {

        checkIsRouteAlreadySaved(request);
        Route routeResponse = buildRouteAndSave(request);

        return RouteResponse.builder()
                .id(routeResponse.getId())
                .routeName(routeResponse.getRouteName())
                .airlineCompanyName(routeResponse.getAirlineCompany().getName())
                .departureAirportName(routeResponse.getDepartureAirport().getName())
                .arrivalAirportName(routeResponse.getArrivalAirport().getName())
                .flightTime(routeResponse.getFlightTime())
                .build();
    }

    @Transactional(readOnly = true)
    public Route getReferenceById(Long routeId) {
        return routeRepository.getReferenceById(routeId);
    }

    private Route buildRouteAndSave(RouteRequest request) {

        Route route = Route.builder()
                .routeName(request.getRouteName())
                .airlineCompany(airlineCompanyService.getReferenceById(request.getAirlineCompanyId()))
                .departureAirport(airportService.getReferenceById(request.getDepartureAirportId()))
                .arrivalAirport(airportService.getReferenceById(request.getArrivalAirportId()))
                .flightTime(request.getFlightTime())
                .build();
        return routeRepository.save(route);
    }

    private void checkIsRouteAlreadySaved(RouteRequest request) {
        boolean routeByName = routeRepository.existsByRouteName(request.getRouteName());
        if (routeByName) {
            throw new RouteAlreadySavedException("Bu rota daha önce eklenmiş");
        }
    }
}
