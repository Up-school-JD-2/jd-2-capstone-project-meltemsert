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

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<CityResponse> getAllCities() {
        return cityRepository.findAll().stream().map(city -> entityToResponse(city)).toList();
    }

    @Transactional
    public CityResponse save(CityRequest request) {
        checkIsCityAlreadySaved(request);

        City city = City.builder()
                .name(request.getName())
                .build();

        City savedCity = cityRepository.save(city);

        return entityToResponse(savedCity);

    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public City getReferenceById(Long id) {
        return cityRepository.getReferenceById(id);
    }

    public CityResponse entityToResponse(City city) {
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .build();

    }

    private void checkIsCityAlreadySaved(CityRequest request) {
        boolean cityByName = cityRepository.existsByName(request.getName());
        if (cityByName) {
            throw new CityAlreadySavedException("This city has already been registered");
        }
    }
}
