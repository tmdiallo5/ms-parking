package com.parking.ms_parking.booking;

import com.parking.ms_parking.car.Car;
import com.parking.ms_parking.car.CarDto;
import com.parking.ms_parking.parkingspot.Parkingspot;
import com.parking.ms_parking.parkingspot.ParkingspotDto;
import com.parking.ms_parking.shared.enums.StatusEnum;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingDto entityToDto(Booking entity) {
       StatusEnum current = entity.getStatuses().iterator().next().getLabel();
        Car car = entity.getCar();
        Parkingspot parkingspot = entity.getParkingspot();
        return new BookingDto(
                entity.getId(),
                entity.getStartDateTime(),
                entity.getEndDateTime(),
                current,
                new CarDto(car.getId(), car.getRegistrationNumber(), car.getType()),
                new ParkingspotDto(parkingspot.getId(), parkingspot.getSpot(), parkingspot.getType())
        );
    }

}


