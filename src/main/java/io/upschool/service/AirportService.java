package io.upschool.service;

import io.upschool.dto.airport.AirportSaveRequest;
import io.upschool.dto.airport.AirportSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.entity.City;
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

    public List<AirportSaveResponse> getAllAirports() {
        return airportRepository.findAll().stream().map(airport -> AirportSaveResponse.builder()
                .id(airport.getId())
                .name(airport.getName())
                .codeName(airport.getCodeName())
                .cityName(airport.getName())
                .build()).collect(Collectors.toList());
    }
    @Transactional
    public AirportSaveResponse save(AirportSaveRequest request) {
        checkIsAirportAlreadySaved(request);
        City cityByReference = cityService.getReferenceById(request.getCityId());
        var newAirport = Airport
                .builder()
                .name(request.getName())
                .codeName(request.getCodeName())
                .city(cityByReference)
                .build();
        Airport savedAirport = airportRepository.save(newAirport);
        return AirportSaveResponse
                .builder()
                .id(savedAirport.getId())
                .name(savedAirport.getName())
                .codeName(savedAirport.getCodeName())
                .cityName(savedAirport.getCity().getName())
                .build();
    }
    public void checkIsAirportAlreadySaved(AirportSaveRequest request) {
        boolean airportByName = airportRepository.existsByName(request.getName());
        if (airportByName) {
            throw new AirportAlreadySavedException("Bu airport daha önce eklenmiş.");
        }
    }
    @Transactional(readOnly = true)
    public Airport getReferenceById(Long id) {
        return airportRepository.getReferenceById(id);
    }
}
