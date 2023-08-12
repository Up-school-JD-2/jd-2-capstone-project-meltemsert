package io.upschool.service;

import io.upschool.dto.airport.AirportRequest;
import io.upschool.dto.airport.AirportResponse;
import io.upschool.entity.Airport;
import io.upschool.exception.AirportAlreadySavedException;
import io.upschool.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final CityService cityService;

    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream().map(airport -> AirportResponse.builder()
                .id(airport.getId())
                .name(airport.getName())
                .iataCode(airport.getIataCode())
                .cityName(airport.getCity().getName())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public AirportResponse save(AirportRequest request) {

        checkIsAirportAlreadySaved(request);
        Airport airportResponse = buildAirportAndSave(request);

        return AirportResponse
                .builder()
                .id(airportResponse.getId())
                .name(airportResponse.getName())
                .iataCode(airportResponse.getIataCode())
                .cityName(airportResponse.getCity().getName())
                .build();
    }

    @Transactional(readOnly = true)
    public Airport getReferenceById(Long id) {
        return airportRepository.getReferenceById(id);
    }

    private Airport buildAirportAndSave(AirportRequest request) {

        Airport airport = Airport
                .builder()
                .name(request.getName())
                .iataCode(request.getIataCode())
                .city(cityService.getReferenceById(request.getCityId()))
                .build();
        return airportRepository.save(airport);
    }

    private void checkIsAirportAlreadySaved(AirportRequest request) {
        boolean airportByName = airportRepository.existsByName(request.getName());
        if (airportByName) {
            throw new AirportAlreadySavedException("This airport has already been registered.");
        }
    }

}
