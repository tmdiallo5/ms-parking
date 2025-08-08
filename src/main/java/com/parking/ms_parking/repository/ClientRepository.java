package com.parking.ms_parking.repository;

import com.parking.ms_parking.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Profile, Integer> {
    Optional<Profile> findByEmail(String email);
}
