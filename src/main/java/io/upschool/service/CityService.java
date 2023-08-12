package io.upschool.service;

import io.upschool.dto.city.CityRequest;
import io.upschool.dto.city.CityResponse;
import io.upschool.entity.City;
import io.upschool.exception.CityAlreadySavedException;
import io.upschool.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<CityResponse> getAllCities() {

        return cityRepository.findAll().stream().map(city -> CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public CityResponse save(CityRequest request) {

        checkIsCityAlreadySaved(request);
        City cityResponse = buildCityAndSave(request);
        return CityResponse.builder()
                .id(cityResponse.getId())
                .name(cityResponse.getName())
                .build();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public City getReferenceById(Long id) {
        return cityRepository.getReferenceById(id);
    }

    private City buildCityAndSave(CityRequest request) {

        City city = City.builder()
                .name(request.getName())
                .build();
        return cityRepository.save(city);
    }

    private void checkIsCityAlreadySaved(CityRequest request) {

        boolean cityByName = cityRepository.existsByName(request.getName());
        if (cityByName) {
            throw new CityAlreadySavedException("This city has already been registered");
        }
    }
}
