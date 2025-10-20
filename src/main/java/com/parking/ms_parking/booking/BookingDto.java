package com.parking.ms_parking.booking;

import com.parking.ms_parking.car.Car;
import com.parking.ms_parking.car.CarDto;
import com.parking.ms_parking.parkingspot.Parkingspot;
import com.parking.ms_parking.parkingspot.ParkingspotDto;
import com.parking.ms_parking.profiles.Profile;
import com.parking.ms_parking.shared.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record BookingDto (
        int id,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        Status status,
        CarDto car,
        ParkingspotDto parkingspot
) {
}

