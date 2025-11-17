package com.parking.ms_parking.parkingspot;

import com.parking.ms_parking.parking.Parking;
import com.parking.ms_parking.parking.ParkingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ParkingSpotService {

    private final ParkingspotRepository parkingspotRepository;
    private final ParkingsRepository parkingsRepository;
    private final ParkingspotMapper parkingspotMapper;

    public void createParkingSpot(int parkingID, List<Parkingspot> parkingspot) {
        Parking parking = this.parkingsRepository.findById(parkingID);
        for (Parkingspot spot : parkingspot) {
            spot.setParking(parking);
        }

        parkingspotRepository.saveAll(parkingspot);
    }

    public Set<ParkingspotDto> getParkingspot(int parkingId) {
        List<Parkingspot> parkings  =this.parkingspotRepository.findByParkingId(parkingId);

        return parkings.stream().map(this.parkingspotMapper::mapParkingspotToDto).collect(Collectors.toSet());
    }
}
