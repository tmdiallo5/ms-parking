package com.parking.ms_parking.parkingspot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingspotRepository extends JpaRepository<Parkingspot, Integer> {
    Optional<Parkingspot> findBySpot(String spot);
    List<Parkingspot> findByParkingId (int parkingId);
}
