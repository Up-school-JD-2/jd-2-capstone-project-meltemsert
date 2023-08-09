package io.upschool.service;

import io.upschool.dto.airlinecompany.AirlineCompanySaveRequest;
import io.upschool.dto.airlinecompany.AirlineCompanySaveResponse;
import io.upschool.entity.AirlineCompany;
import io.upschool.exception.AirlineCompanyAlreadySavedException;
import io.upschool.repository.AirlineCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirlineCompanyService {
    private final AirlineCompanyRepository airlineCompanyRepository;

    public List<AirlineCompanySaveResponse> getAllAirlineCompanies() {

        return airlineCompanyRepository.findAll().stream().map(airlineCompany ->
                AirlineCompanySaveResponse.builder()
                        .id(airlineCompany.getId())
                        .name(airlineCompany.getName())
                        .codeName(airlineCompany.getCodeName())
                        .build()).collect(Collectors.toList());
    }

    public AirlineCompanySaveResponse save(AirlineCompanySaveRequest request) {
        checkIsAirlineCompanyAlreadySaved(request);
        var newAirlineCompany = AirlineCompany.builder()
                .name(request.getName())
                .codeName(request.getCodeName())
                .build();
        AirlineCompany savedAirlineCompany = airlineCompanyRepository.save(newAirlineCompany);

        return AirlineCompanySaveResponse.builder()
                .id(savedAirlineCompany.getId())
                .name(savedAirlineCompany.getName())
                .codeName(savedAirlineCompany.getCodeName())
                .build();
    }

    public AirlineCompany getReferenceById(Long airlineCompanyId) {
        return airlineCompanyRepository.getReferenceById(airlineCompanyId);
    }

    private void checkIsAirlineCompanyAlreadySaved(AirlineCompanySaveRequest request) {
        boolean airlineByName = airlineCompanyRepository.existsByName(request.getName());
        if (airlineByName) {
            throw new AirlineCompanyAlreadySavedException("Bu airline company daha önce eklenmiş");
        }
    }
}
