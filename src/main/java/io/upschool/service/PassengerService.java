package io.upschool.service;


import io.upschool.dto.passenger.PassengerRequest;
import io.upschool.dto.passenger.PassengerResponse;
import io.upschool.entity.City;
import io.upschool.entity.Passenger;
import io.upschool.exception.PassengerAlreadySavedException;
import io.upschool.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public List<PassengerResponse> getAllPassengers(){

        return passengerRepository.findAll().stream().map(passenger -> PassengerResponse.builder()
                .id(passenger.getId())
                .nameSurname(passenger.getName()  + " " + passenger.getSurname())
                .identificationNumber(passenger.getIdentificationNumber())
                .email(passenger.getEmail())
                .contactNumber(passenger.getContactNumber())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public PassengerResponse save(PassengerRequest request) {

        checkIsPassengerAlreadySaved(request);
        Passenger passengerResponse=buildPassengerAndSave(request);
        return PassengerResponse.builder()
                .id(passengerResponse.getId())
                .nameSurname(passengerResponse.getName() + " " + passengerResponse.getSurname())
                .identificationNumber(passengerResponse.getIdentificationNumber())
                .email(passengerResponse.getEmail())
                .contactNumber(passengerResponse.getContactNumber())
                .build();
    }
    @Transactional(readOnly = true)
    public Passenger getReferenceById(Long passengerId) {
        return passengerRepository.getReferenceById(passengerId);
    }
    private Passenger buildPassengerAndSave(PassengerRequest request) {

        Passenger passenger = Passenger.builder()
                .name(request.getName())
                .surname(request.getSurName())
                .identificationNumber(request.getIdentificationNumber())
                .email(request.getEmail())
                .contactNumber(request.getContactNumber())
                .build();
        return passengerRepository.save(passenger);
    }
    private void checkIsPassengerAlreadySaved(PassengerRequest request){
        boolean passengerByIdentificationNumber=
                passengerRepository.existsByidentificationNumber(request.getIdentificationNumber());
        if(passengerByIdentificationNumber){
            throw new PassengerAlreadySavedException("This passenger has already been registered");
        }
    }
}



