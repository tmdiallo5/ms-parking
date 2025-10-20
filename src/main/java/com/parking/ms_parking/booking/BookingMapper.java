package com.parking.ms_parking.booking;

import com.parking.ms_parking.car.Car;
import com.parking.ms_parking.car.CarDto;
import com.parking.ms_parking.parkingspot.Parkingspot;
import com.parking.ms_parking.parkingspot.ParkingspotDto;
import com.parking.ms_parking.profiles.Profile;
import com.parking.ms_parking.shared.enums.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BookingMapper {

    public BookingDto entityToDto(Booking entity) {
        Car car = entity.getCar();
        Parkingspot parkingspot = entity.getParkingspot();
        return new BookingDto(
                entity.getId(),
                entity.getStartDateTime(),
                entity.getEndDateTime(),
                entity.getStatus(),
                new CarDto(car.getId(), car.getRegistrationNumber(), car.getType()),
                new ParkingspotDto(parkingspot.getId(), parkingspot.getSpot(), parkingspot.getType())
        );
    }

}


