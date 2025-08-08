package com.parking.ms_parking.parking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingsRepository extends JpaRepository<Parking, Integer> {
    Optional<Parking> findByName(String name);
}
