package io.upschool.service;

import io.upschool.dto.city.CitySaveRequest;
import io.upschool.dto.city.CitySaveResponse;
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

    public List <CitySaveResponse> getAllCities(){
        return cityRepository.findAll().stream().map(city -> CitySaveResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .build()).collect(Collectors.toList());
    }

    public CitySaveResponse save(CitySaveRequest request){
        checkIsCityAlreadySaved(request);
        City cityResponse=buildCityAndSave(request);
        return CitySaveResponse.builder()
                .id(cityResponse.getId())
                .name(cityResponse.getName())
                .build();
    }
    private City buildCityAndSave(CitySaveRequest request) {
        City newCity = City.builder()
                .name(request.getName())
                .build();
        return cityRepository.save(newCity);
    }

    private void checkIsCityAlreadySaved(CitySaveRequest request) {
        boolean cityByName= cityRepository.existsByName(request.getName());
        if (cityByName) {
            throw new CityAlreadySavedException("Bu city daha önce eklenmiş");
        }
    }
    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public City getReferenceById(Long id) {
        return cityRepository.getReferenceById(id);
    }
}
