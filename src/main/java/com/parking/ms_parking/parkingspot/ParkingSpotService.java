package com.parking.ms_parking.parkingspot;

import com.parking.ms_parking.parking.Parking;
import com.parking.ms_parking.parking.ParkingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ParkingSpotService {

    private final ParkingspotRepository parkingspotRepository;
    private final ParkingsRepository parkingsRepository;

    public void createParkingSpot(int parkingID, List<Parkingspot> parkingspot) {
        Parking parking = this.parkingsRepository.findById(parkingID);
        for (Parkingspot spot : parkingspot) {
            spot.setParking(parking);
        }

        parkingspotRepository.saveAll(parkingspot);
    }
}
