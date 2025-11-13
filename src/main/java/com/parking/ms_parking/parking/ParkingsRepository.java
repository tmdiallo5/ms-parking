package com.parking.ms_parking.parking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingsRepository extends JpaRepository<Parking, Integer> {
    Parking findById(int id);

}
