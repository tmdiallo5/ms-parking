package com.parking.ms_parking.parkingspot;

import org.springframework.stereotype.Component;

@Component
public class ParkingspotMapper {
    public ParkingspotDto mapParkingspotToDto(Parkingspot entity) {
        return new ParkingspotDto(
                entity.getId(),
                entity.getSpot(),
                entity.getType()
        );
    }
}
