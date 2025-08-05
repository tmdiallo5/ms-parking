package com.parking.ms_parking.entities;

import com.parking.ms_parking.shared.entities.Address;
import jakarta.persistence.*;

public record ClientDTO(
         int id,
         String firstName,
         String lastName,
         String email,
         String password

) {
}
