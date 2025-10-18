package com.parking.ms_parking.booking;

import com.parking.ms_parking.car.Car;
import com.parking.ms_parking.car.CarRepository;
import com.parking.ms_parking.parking.ParkingsRepository;
import com.parking.ms_parking.parkingspot.Parkingspot;
import com.parking.ms_parking.parkingspot.ParkingspotRepository;
import com.parking.ms_parking.profiles.*;
import com.parking.ms_parking.security.services.SecurityService;
import com.parking.ms_parking.shared.services.AddressesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookingService {

    private final ParkingsRepository parkingsRepository;
    private final AddressesRepository addressesRepository;
    private final ParkingspotRepository parkingspotRepository;
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;
    private final ProfileRepository profileRepository;
    private final SecurityService securityService;


    private boolean checkBookingPossible (Booking booking){
        for (Booking availableBooking : bookingRepository.findAll()){
            if (availableBooking.getParkingspot().getSpot().equals(booking.getParkingspot().getSpot())){
                if (availableBooking.getStartDateTime().isEqual(booking.getStartDateTime())
                        && availableBooking.getEndDateTime().isEqual(booking.getEndDateTime())){
                    return false;
                }
                if (availableBooking.getStartDateTime().isEqual(booking.getStartDateTime())
                        && availableBooking.getEndDateTime().isAfter(booking.getEndDateTime())){
                    return false;
                }
                if (availableBooking.getStartDateTime().isAfter(booking.getStartDateTime())
                        && availableBooking.getEndDateTime().isBefore(booking.getEndDateTime())){
                    return false;
                }
                if (booking.getStartDateTime().isAfter(availableBooking.getStartDateTime())
                        && booking.getEndDateTime().isBefore(availableBooking.getEndDateTime())){
                    return false;
                }
                if (booking.getStartDateTime().isBefore(availableBooking.getEndDateTime())
                        && booking.getEndDateTime().isAfter(availableBooking.getEndDateTime())){
                    return false;
                }
                if (availableBooking.getEndDateTime().isEqual(booking.getEndDateTime())
                        && booking.getStartDateTime().isAfter(availableBooking.getStartDateTime())) {
                    return false;
                }
                if (booking.getStartDateTime().isBefore(availableBooking.getStartDateTime())
                        && booking.getEndDateTime().isAfter(availableBooking.getStartDateTime())) {
                    return false;
                }
                if (booking.getStartDateTime().isEqual(booking.getEndDateTime())) {
                    return false;
                }


            }
        }
        return true;

    }

    public void createBooking(Booking booking) {
       Profile profile =  this.securityService.getCurrentProfile();
        booking.setProfile(profile);

        if (!checkBookingPossible(booking)){
            throw new RuntimeException("Booking not possible");
        }

       if (booking.getStartDateTime()==null || booking.getEndDateTime()==null) {
           throw new IllegalArgumentException("startDateTime and endDateTime fields are required");
       }

       if (booking.getParkingspot().getSpot()!=null) {
          Optional<Parkingspot> spot =  this.parkingspotRepository.findBySpot(booking.getParkingspot().getSpot());
          booking.setParkingspot(spot.orElseThrow(() -> new RuntimeException("Parkingspot not found")));
       }

       if(booking.getCar()==null || booking.getCar().getRegistrationNumber()==null) {
           throw new IllegalArgumentException("carRegistrationNumber field is required");
       }
        String reg = booking.getCar().getRegistrationNumber();
       Car car;
        Optional<Car> optionalCar = this.carRepository.findByRegistrationNumberAndProfile(reg, profile);
        if (optionalCar.isPresent()) {
            car = optionalCar.get();
        }
        else {
            Car car1 = new Car();
            car1.setRegistrationNumber(reg);
            car1.setProfile(profile);
            car1.setType(booking.getCar().getType());
            car = this.carRepository.save(car1);
        }
        booking.setCar(car);




        this.bookingRepository.save(booking);

    }




    public List<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }


}
