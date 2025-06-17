package com.parking.ms_parking.repository;

import com.parking.ms_parking.entities.Booking;
import com.parking.ms_parking.entities.Parkingspot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Optional<Booking> findByParkingspotNumber(String number);
}
