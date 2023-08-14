package io.upschool.service;

import io.upschool.dto.airlinecompany.AirlineCompanyRequest;
import io.upschool.dto.airlinecompany.AirlineCompanyResponse;
import io.upschool.dto.airlinecompany.AirlineCompanySearchRequest;
import io.upschool.entity.AirlineCompany;
import io.upschool.exception.AirlineCompanyAlreadySavedException;
import io.upschool.repository.AirlineCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineCompanyService {
    private final AirlineCompanyRepository airlineCompanyRepository;

    public List<AirlineCompanyResponse> getAllAirlineCompanies() {
        return airlineCompanyRepository.findAll().stream().map(airlineCompany ->
                entityToResponse(airlineCompany)).toList();
    }

    @Transactional
    public AirlineCompanyResponse save(AirlineCompanyRequest request) {
        checkIsAirlineCompanyAlreadySaved(request);

        AirlineCompany airlineCompany = AirlineCompany.builder()
                .name(request.getName())
                .iataCode(request.getIataCode())
                .build();
        AirlineCompany savedAirlineCompany = airlineCompanyRepository.save(airlineCompany);

        return entityToResponse(savedAirlineCompany);
    }

    @Transactional(readOnly = true)
    public AirlineCompany getReferenceById(Long airlineCompanyId) {
        return airlineCompanyRepository.getReferenceById(airlineCompanyId);
    }

    public AirlineCompanyResponse entityToResponse(AirlineCompany airlineCompany) {
        return AirlineCompanyResponse.builder()
                .id(airlineCompany.getId())
                .name(airlineCompany.getName())
                .iataCode(airlineCompany.getIataCode())
                .build();
    }

    private void checkIsAirlineCompanyAlreadySaved(AirlineCompanyRequest request) {
        boolean airlineByName = airlineCompanyRepository.existsByName(request.getName());
        if (airlineByName) {
            throw new AirlineCompanyAlreadySavedException("This airline company has already been registered");
        }
    }

    public List<AirlineCompanyResponse> search(AirlineCompanySearchRequest request) {
        AirlineCompany airlineCompany = AirlineCompany.builder()
                .name(request.getName())
                .iataCode(request.getIataCode())
                .build();

        Example<AirlineCompany> airlineCompanyExample = Example.of(airlineCompany,
                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        List<AirlineCompany> airlineCompanies = airlineCompanyRepository.findAll(airlineCompanyExample);

        return airlineCompanies.stream().map(ac -> entityToResponse(ac)).toList();
    }
}
