package com.parking.ms_parking.repository;

import com.parking.ms_parking.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByImmatriculation(String immatriculation);
}
