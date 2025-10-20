package com.parking.ms_parking.car;

import com.parking.ms_parking.profiles.Profile;
import jakarta.persistence.*;

public record CarDto(
        int id,
        String registrationNumber,
        String type
) {
}

