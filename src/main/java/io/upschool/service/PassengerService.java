package io.upschool.service;

import io.upschool.dto.passenger.PassengerSaveRequest;
import io.upschool.dto.passenger.PassengerSaveResponse;
import io.upschool.entity.Passenger;
import io.upschool.entity.Route;
import io.upschool.exception.FlightAlreadySavedException;
import io.upschool.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public List<Passenger> getAllPassengers(){
      return passengerRepository.findAll();
    }

    public PassengerSaveResponse save(PassengerSaveRequest request) {
        var newPassenger= Passenger.builder()
                .name(request.getName())
                .surname(request.getSurName())
                .email(request.getEmail())
                .contactNumber(request.getContactNumber())
                .build();
        Passenger savedPassenger=passengerRepository.save(newPassenger);
        return PassengerSaveResponse.builder()
                .id(savedPassenger.getId())
                .nameSurname(savedPassenger.getName() + " " + savedPassenger.getSurname())
                .email(savedPassenger.getEmail())
                .contactNumber(savedPassenger.getContactNumber())
                .build();
    }

    public void checkIsPassengerAlreadySaved(PassengerSaveRequest request){
        boolean passengerByByidentificationNumber=
                passengerRepository.existsByidentificationNumber(request.getIdentificationNumber());
        if(passengerByByidentificationNumber){
            throw new FlightAlreadySavedException("Bu uçuş daha önce eklenmiş");
        }
    }
    public Passenger getReferenceById(Long passengerId) {
        return passengerRepository.getReferenceById(passengerId);
    }
}



