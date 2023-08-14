package io.upschool.service;

import io.upschool.dto.airport.AirportRequest;
import io.upschool.dto.airport.AirportResponse;
import io.upschool.dto.airport.AirportSearchRequest;
import io.upschool.entity.Airport;
import io.upschool.exception.AirportAlreadySavedException;
import io.upschool.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final CityService cityService;

    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream().map(airport -> entityToResponse(airport)).toList();
    }

    @Transactional
    public AirportResponse save(AirportRequest request) {
        checkIsAirportAlreadySaved(request);

        Airport airport = Airport
                .builder()
                .name(request.getName())
                .iataCode(request.getIataCode())
                .city(cityService.getReferenceById(request.getCityId()))
                .build();

        Airport savedAirport = airportRepository.save(airport);

        return entityToResponse(savedAirport);

    }

    @Transactional(readOnly = true)
    public Airport getReferenceById(Long id) {
        return airportRepository.getReferenceById(id);
    }

    public AirportResponse entityToResponse(Airport airport) {
        return AirportResponse
                .builder()
                .id(airport.getId())
                .name(airport.getName())
                .iataCode(airport.getIataCode())
                .cityName(airport.getCity().getName())
                .build();
    }

    private void checkIsAirportAlreadySaved(AirportRequest request) {
        boolean airportByName = airportRepository.existsByName(request.getName());
        if (airportByName) {
            throw new AirportAlreadySavedException("This airport has already been registered.");
        }
    }

    public List<AirportResponse> search(AirportSearchRequest request) {
        Airport airport = Airport.builder()
                .name(request.getName())
                .iataCode(request.getIataCode())
                .build();
        Example<Airport> airportExample = Example.of(airport,
                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        List<Airport> airports = airportRepository.findAll(airportExample);

        return airports.stream().map(ap -> entityToResponse(ap)).toList();
    }
}
