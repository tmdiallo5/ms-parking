package tech.mavi.ms_parking.shared.entities.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByStreetContainingIgnoreCaseOrCityContainingIgnoreCaseOrZipContainingIgnoreCase(
            String street,
            String zip,
            String city
    );
}
