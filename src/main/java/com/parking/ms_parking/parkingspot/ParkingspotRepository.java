package com.parking.ms_parking.parkingspot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingspotRepository extends JpaRepository<Parkingspot, Integer> {
    Optional<Parkingspot> findByNumber(String number);
}
