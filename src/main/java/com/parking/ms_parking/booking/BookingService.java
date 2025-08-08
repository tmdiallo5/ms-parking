package com.parking.ms_parking.booking;

import com.parking.ms_parking.car.Car;
import com.parking.ms_parking.car.CarRepository;
import com.parking.ms_parking.parking.Parking;
import com.parking.ms_parking.parking.ParkingsRepository;
import com.parking.ms_parking.parkingspot.Parkingspot;
import com.parking.ms_parking.parkingspot.ParkingspotRepository;
import com.parking.ms_parking.profiles.*;
import com.parking.ms_parking.shared.entities.Address;
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

    public void createBooking(Booking booking) {
    //Faire une requete voir si le spot est libre. Faire une requete dans la base en fonction du spot

        Optional <Booking> bookingOptional = bookingRepository.findByParkingspotNumber(booking.getParkingspot().getNumber());
        if (bookingOptional.isPresent()) {
            throw new RuntimeException("Booking already exists");
        }

        List<Address> resultAddress = this.addressesRepository.findByTagAndStreetAndCityAndZipAndCountry(
                booking.getProfile().getAddress().getTag(),
                booking.getProfile().getAddress().getStreet(),
                booking.getProfile().getAddress().getCity(),
                booking.getProfile().getAddress().getZip(),
                booking.getProfile().getAddress().getCountry()

        );
        Address address;
        if (!resultAddress.isEmpty()) {
            address = resultAddress.get(0);
        }
        else {
            address = this.addressesRepository.save(booking.getProfile().getAddress());
        }
        booking.getProfile().setAddress(address);


        Optional<Profile> optionalClient = this.profileRepository.findByEmail(booking.getProfile().getEmail());
        Profile profile;
        if (optionalClient.isPresent()) {
            profile = optionalClient.get();
        }
        else {
            profile = this.profileRepository.save(booking.getProfile());
        }


        Optional<Car> optionalCar = this.carRepository.findByImmatriculation(booking.getCar().getImmatriculation());
        Car car;
        if (optionalCar.isPresent()) {
            car = optionalCar.get();
        }
        else {
            booking.getCar().setProfile(profile);
            car = this.carRepository.save(booking.getCar());
        }

        Optional<Parking> optionalParking = this.parkingsRepository.findByName(booking.getParkingspot().getParking().getName());
        Parking parking;
        if (optionalParking.isPresent()) {
            parking = optionalParking.get();
        }
        else {
            parking = this.parkingsRepository.save(booking.getParkingspot().getParking());
        }
        booking.getParkingspot().setParking(parking);


        Optional<Parkingspot> optionalParkingspot = this.parkingspotRepository.findByNumber(booking.getParkingspot().getNumber());
        Parkingspot parkingspot;

        if (optionalParkingspot.isPresent()) {
            parkingspot = optionalParkingspot.get();
        }
        else {

            parkingspot = this.parkingspotRepository.save(booking.getParkingspot());
        }
        car.setProfile(profile);
        booking.setCar(car);
        booking.setParkingspot(parkingspot);
        booking.setProfile(profile);



        this.bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }

}
