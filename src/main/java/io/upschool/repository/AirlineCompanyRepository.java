package io.upschool.repository;


import io.upschool.entity.AirlineCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany, Long> {
    boolean  existsByName (String name);
}
