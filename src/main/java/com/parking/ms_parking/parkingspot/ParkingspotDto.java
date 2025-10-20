package com.parking.ms_parking.parkingspot;

import com.parking.ms_parking.parking.Parking;
import jakarta.persistence.*;

public record ParkingspotDto(
        int id,
        String spot,
        String type
) {
}

