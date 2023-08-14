package io.upschool.service;

import io.upschool.dto.passenger.PassengerRequest;
import io.upschool.dto.passenger.PassengerResponse;
import io.upschool.entity.Passenger;
import io.upschool.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;

    @Transactional
    public Passenger save(PassengerRequest request) {
        if (checkIsPassengerAlreadySaved(request)) {
            return passengerRepository.findByIdentificationNumber(request.getIdentificationNumber());
        } else {
            Passenger passenger = Passenger.builder()
                    .identificationNumber(request.getIdentificationNumber())
                    .name(request.getName())
                    .surname(request.getSurName())
                    .email(request.getEmail())
                    .contactNumber(request.getContactNumber())
                    .build();
            return passengerRepository.save(passenger);
        }
    }

    public PassengerResponse entityToResponse(Passenger passenger) {
        return PassengerResponse.builder()
                .id(passenger.getId())
                .nameSurname(passenger.getName() + " " + passenger.getSurname())
                .identificationNumber(passenger.getIdentificationNumber())
                .email(passenger.getEmail())
                .contactNumber(passenger.getContactNumber())
                .build();

    }

    public boolean checkIsPassengerAlreadySaved(PassengerRequest request) {
        boolean passengerByIdentificationNumber =
                passengerRepository.existsByIdentificationNumber(request.getIdentificationNumber());
        return passengerByIdentificationNumber;
    }
}



