package com.parking.ms_parking.parking;


import com.parking.ms_parking.parkingspot.ParkingSpotService;
import com.parking.ms_parking.profiles.Profile;
import com.parking.ms_parking.profiles.ProfileRepository;
import com.parking.ms_parking.profiles.Role;
import com.parking.ms_parking.profiles.RolesRepository;
import com.parking.ms_parking.security.services.SecurityService;
import com.parking.ms_parking.shared.entities.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ParkingService {
    private final SecurityService securityService;
    private final ParkingsRepository parkingsRepository;
    private final RolesRepository rolesRepository;
    private final ProfileRepository profileRepository;
    private final ParkingMapper parkingMapper;

    private boolean sameAddress(Address a1, Address a2) {
        return (a1.getStreet().equals(a2.getStreet()));
    }

    private void createParkingPossible(Parking parking) {
        for(Parking parc : parkingsRepository.findAll()) {
            if (parc.getName().equals(parking.getName()) ) {
                throw new RuntimeException("This parking already exists.");
            }
            if (sameAddress(parking.getAddress(), parc.getAddress())) {
                throw new RuntimeException("A parking already exists at this location.");
            }
        }

    }

    public void createParking(Parking parking) {
       Profile profile =  this.securityService.getCurrentProfile();
       parking.setOwner(profile);

       createParkingPossible(parking);

       Role role =  this.rolesRepository.findByName("PARKING_MANAGER");
       profile.setRole(role);
       profileRepository.save(profile);


        this.parkingsRepository.save(parking);
    }


    public Set<ParkingDto> getAllParking() {
        List<Parking> parkings = this.parkingsRepository.findAll();
        return parkings.stream().map(this.parkingMapper::mapParkingToDto).collect(Collectors.toSet());
    }
}
