package com.parking.ms_parking.parking;

import org.springframework.stereotype.Component;

@Component
public class ParkingMapper {

    public ParkingDto mapParkingToDto(Parking parkingEntity) {
        return  new ParkingDto(
                parkingEntity.getId(),
                parkingEntity.getName(),
                parkingEntity.getAddress()
        );
    }
}
