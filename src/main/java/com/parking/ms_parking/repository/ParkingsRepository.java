package com.parking.ms_parking.repository;

import com.parking.ms_parking.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingsRepository extends JpaRepository<Parking, Integer> {
    Optional<Parking> findByName(String name);
}
