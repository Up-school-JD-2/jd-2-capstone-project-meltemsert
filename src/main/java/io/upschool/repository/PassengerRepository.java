package io.upschool.repository;

import io.upschool.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {

    boolean existsByidentificationNumber(String number);
}
