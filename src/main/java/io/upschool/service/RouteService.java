package io.upschool.service;

import io.upschool.dto.route.RouteRequest;
import io.upschool.dto.route.RouteResponse;
import io.upschool.dto.route.RouteSearchRequest;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.exception.RouteAlreadySavedException;
import io.upschool.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final AirportService airportService;
    private final AirlineCompanyService airlineCompanyService;

    public List<RouteResponse> getAllRoutes() {
        return routeRepository.findAll().stream().map(route -> entityToResponse(route)).toList();
    }

    @Transactional
    public RouteResponse save(RouteRequest request) {
        checkIsRouteAlreadySaved(request);

        Route route = Route.builder()
                .routeName(request.getRouteName())
                .departureAirport(airportService.getReferenceById(request.getDepartureAirportId()))
                .arrivalAirport(airportService.getReferenceById(request.getArrivalAirportId()))
                .build();
        Route savedRoute = routeRepository.save(route);

        return entityToResponse(savedRoute);
    }

    @Transactional(readOnly = true)
    public Route getReferenceById(Long routeId) {
        return routeRepository.getReferenceById(routeId);
    }

    public RouteResponse entityToResponse(Route route) {
        return RouteResponse.builder()
                .id(route.getId())
                .routeName(route.getRouteName())
                .departureAirportName(route.getDepartureAirport().getName())
                .arrivalAirportName(route.getArrivalAirport().getName())
                .build();
    }

    private void checkIsRouteAlreadySaved(RouteRequest request) {
        boolean routeByName = routeRepository.existsByRouteName(request.getRouteName());
        if (routeByName) {
            throw new RouteAlreadySavedException("This route has already been registered");
        }
    }

    public List<RouteResponse> search(RouteSearchRequest request) {
        var routeBuilder = Route.builder();

        if (request.getDeparture() != null) {
            Airport departureAirport = Airport.builder()
                    .name(request.getDeparture().getName())
                    .build();
            routeBuilder.departureAirport(departureAirport);
        }
        if (request.getArrival() != null) {
            Airport arrivalAirport = Airport.builder()
                    .name(request.getArrival().getName())
                    .build();
            routeBuilder.arrivalAirport(arrivalAirport);
        }
        if (request.getRouteName() != null) {
            routeBuilder.routeName(request.getRouteName());
        }
        Route route = routeBuilder.build();

        Example<Route> routeExample = Example.of(route,
                ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        List<Route> routes = routeRepository.findAll(routeExample);
        return routes.stream().map(r -> entityToResponse(r)).toList();
    }
}
