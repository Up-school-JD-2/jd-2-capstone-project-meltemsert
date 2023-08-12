package io.upschool.service;

import io.upschool.dto.airlinecompany.AirlineCompanyRequest;
import io.upschool.dto.airlinecompany.AirlineCompanyResponse;
import io.upschool.dto.creditcard.CreditCardRequest;
import io.upschool.entity.AirlineCompany;
import io.upschool.entity.CreditCard;
import io.upschool.exception.AirlineCompanyAlreadySavedException;
import io.upschool.repository.AirlineCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirlineCompanyService {
    private final AirlineCompanyRepository airlineCompanyRepository;

    public List<AirlineCompanyResponse> getAllAirlineCompanies() {

        return airlineCompanyRepository.findAll().stream().map(airlineCompany ->
                AirlineCompanyResponse.builder()
                        .id(airlineCompany.getId())
                        .name(airlineCompany.getName())
                        .iataCode(airlineCompany.getIataCode())
                        .build()).collect(Collectors.toList());
    }

    @Transactional
    public AirlineCompanyResponse save(AirlineCompanyRequest request) {

        checkIsAirlineCompanyAlreadySaved(request);
        AirlineCompany airlineCompanyResponse = buildCreditCardAndSave(request);

        return AirlineCompanyResponse.builder()
                .id(airlineCompanyResponse.getId())
                .name(airlineCompanyResponse.getName())
                .iataCode(airlineCompanyResponse.getIataCode())
                .build();
    }

    @Transactional(readOnly = true)
    public AirlineCompany getReferenceById(Long airlineCompanyId) {
        return airlineCompanyRepository.getReferenceById(airlineCompanyId);
    }

    private AirlineCompany buildCreditCardAndSave(AirlineCompanyRequest request) {

        AirlineCompany airlineCompany = AirlineCompany.builder()
                .name(request.getName())
                .iataCode(request.getIataCode())
                .build();
        return airlineCompanyRepository.save(airlineCompany);
    }

    private void checkIsAirlineCompanyAlreadySaved(AirlineCompanyRequest request) {
        boolean airlineByName = airlineCompanyRepository.existsByName(request.getName());
        if (airlineByName) {
            throw new AirlineCompanyAlreadySavedException("This airline company has already been registered");
        }
    }
}
