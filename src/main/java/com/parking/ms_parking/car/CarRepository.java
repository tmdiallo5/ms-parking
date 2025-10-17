package com.parking.ms_parking.car;


import com.parking.ms_parking.profiles.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByRegistrationNumberAndProfile(String registrationNumber, Profile Profile);
}
