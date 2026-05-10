package tech.mavi.ms_parking.parkings;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {
    List<Parking> findByAddressCityContainsOrAddressStreetContainsOrAddressZipContains(
            String city,
            String street,
            String zip
    );
}
