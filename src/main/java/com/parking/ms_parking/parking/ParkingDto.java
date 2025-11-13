package com.parking.ms_parking.parking;

import com.parking.ms_parking.profiles.Profile;
import com.parking.ms_parking.shared.entities.Address;
import jakarta.persistence.*;

public record ParkingDto(
        int id,
        String name,
        Address address
) {
}


