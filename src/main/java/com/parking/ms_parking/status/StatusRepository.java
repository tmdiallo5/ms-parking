package com.parking.ms_parking.status;

import com.parking.ms_parking.shared.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {
   Optional<Status> findByLabel(StatusEnum label);
}
