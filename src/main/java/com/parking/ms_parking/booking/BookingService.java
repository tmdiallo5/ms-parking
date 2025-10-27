package com.parking.ms_parking.booking;

import com.parking.ms_parking.car.Car;
import com.parking.ms_parking.car.CarRepository;
import com.parking.ms_parking.parking.ParkingsRepository;
import com.parking.ms_parking.parkingspot.Parkingspot;
import com.parking.ms_parking.parkingspot.ParkingspotRepository;
import com.parking.ms_parking.profiles.*;
import com.parking.ms_parking.security.services.SecurityService;
import com.parking.ms_parking.shared.enums.StatusEnum;
import com.parking.ms_parking.shared.services.AddressesRepository;

import com.parking.ms_parking.status.Status;
import com.parking.ms_parking.status.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookingService {

    private final ParkingsRepository parkingsRepository;
    private final AddressesRepository addressesRepository;
    private final ParkingspotRepository parkingspotRepository;
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;
    private final SecurityService securityService;
    private final BookingMapper bookingMapper;
    private final StatusRepository statusRepository;

    private boolean checkBookingPossible (Booking booking){
        for (Booking availableBooking : bookingRepository.findAll()){
            if (booking.getStartDateTime().isAfter(booking.getEndDateTime())){
                return false;
            }
            if (booking.getStartDateTime().isEqual(booking.getEndDateTime())){
                return false;
            }
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



       Status status = this.statusRepository.findByLabel(StatusEnum.BOOKED)
               .orElseThrow(()-> new RuntimeException("Status not found"));
       booking.getStatuses().add(status);





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


    public Set<BookingDto> getAllBookings() {
        List<Booking> bookings = this.bookingRepository.findAll();
       return bookings.stream().map(this.bookingMapper::entityToDto).collect(Collectors.toSet());
    }

    public BookingDto rechercheByID(int id) {
        Booking bookingEntity  = this.bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));

        return bookingMapper.entityToDto(bookingEntity);
    }



    public Booking updateBooking(int id, Booking booking) {
      Booking booking1 = this.bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
      booking1.setStartDateTime(booking.getStartDateTime());
      booking1.setEndDateTime(booking.getEndDateTime());
      booking1.setStatuses(booking.getStatuses());
      booking1.setParkingspot(booking.getParkingspot());


      return this.bookingRepository.save(booking1);
    }

    public void deleteBooking(int id) {
        this.bookingRepository.deleteById(id);
    }




}




