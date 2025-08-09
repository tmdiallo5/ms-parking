package com.parking.ms_parking.security.activations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ActivationsRepository extends JpaRepository<Activation, Integer> {
    List<Activation> findAllByActiveAndDesactivationAfter(Boolean active, LocalDateTime desactivation);
}
