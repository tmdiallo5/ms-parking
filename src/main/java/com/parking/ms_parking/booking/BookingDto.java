package com.parking.ms_parking.booking;

import com.parking.ms_parking.car.CarDto;
import com.parking.ms_parking.parkingspot.ParkingspotDto;
import com.parking.ms_parking.shared.enums.StatusEnum;

import java.time.LocalDateTime;

public record BookingDto (
        int id,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        StatusEnum statusEnum,
        CarDto car,
        ParkingspotDto parkingspot
) {
}

