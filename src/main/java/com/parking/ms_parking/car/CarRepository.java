package com.parking.ms_parking.car;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByImmatriculation(String immatriculation);
}
