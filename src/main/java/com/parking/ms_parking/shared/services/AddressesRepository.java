package com.parking.ms_parking.shared.services;

import com.parking.ms_parking.shared.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressesRepository extends JpaRepository<Address, Integer> {
}
