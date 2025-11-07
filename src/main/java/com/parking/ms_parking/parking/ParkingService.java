package com.parking.ms_parking.parking;


import com.parking.ms_parking.parkingspot.ParkingSpotService;
import com.parking.ms_parking.profiles.Profile;
import com.parking.ms_parking.profiles.ProfileRepository;
import com.parking.ms_parking.profiles.Role;
import com.parking.ms_parking.profiles.RolesRepository;
import com.parking.ms_parking.security.services.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ParkingService {
    private final SecurityService securityService;
    private final ParkingsRepository parkingsRepository;
    private final RolesRepository rolesRepository;
    private final ProfileRepository profileRepository;


    public void createParking(Parking parking) {
       Profile profile =  this.securityService.getCurrentProfile();
       parking.setOwner(profile);

       Role role =  this.rolesRepository.findByName("PARKING_MANAGER");
       profile.setRole(role);
       profileRepository.save(profile);


        this.parkingsRepository.save(parking);
    }


}
