package com.parking.ms_parking.repository;

import com.parking.ms_parking.entities.Parkingspot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingspotRepository extends JpaRepository<Parkingspot, Integer> {
    Optional<Parkingspot> findByNumber(String number);
}
