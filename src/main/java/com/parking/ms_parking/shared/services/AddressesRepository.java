package com.parking.ms_parking.shared.services;

import com.parking.ms_parking.shared.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AddressesRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findByTagAndStreetAndCityAndZipAndCountry(String tag, String street, String city, String zip, String country);

}
