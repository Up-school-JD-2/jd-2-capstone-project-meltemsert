package io.upschool.repository;

import io.upschool.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    boolean existsByRouteName(String name);
}
