package io.upschool.repository;

import io.upschool.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    boolean existsByIdentificationNumber(String number);

    Passenger findByIdentificationNumber(String number);
}
