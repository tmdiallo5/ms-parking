package com.parking.ms_parking.services;

import com.parking.ms_parking.entities.*;
import com.parking.ms_parking.repository.*;
import com.parking.ms_parking.shared.entities.Address;
import com.parking.ms_parking.shared.services.AddressesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.awt.*;
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
    private final ClientRepository clientRepository;

    public void createBooking(Booking booking) {
    //Faire une requete voir si le spot est libre. Faire une requete dans la base en fonction du spot

        Optional <Booking> bookingOptional = bookingRepository.findByParkingspotNumber(booking.getParkingspot().getNumber());
        if (bookingOptional.isPresent()) {
            throw new RuntimeException("Booking already exists");
        }

        Optional<Address> optionalAddress = this.addressesRepository.findByTagAndStreetAndCityAndZipAndCountry(
                booking.getClient().getAddress().getTag(),
                booking.getClient().getAddress().getStreet(),
                booking.getClient().getAddress().getCity(),
                booking.getClient().getAddress().getZip(),
                booking.getClient().getAddress().getCountry()

        );
        Address address;
        if (optionalAddress.isPresent()) {
            address = optionalAddress.get();
        }
        else {
            address = this.addressesRepository.save(booking.getClient().getAddress());
        }
        booking.getClient().setAddress(address);


        Optional<Client> optionalClient = this.clientRepository.findByEmail(booking.getClient().getEmail());
        Client client;
        if (optionalClient.isPresent()) {
            client = optionalClient.get();
        }
        else {
            client = this.clientRepository.save(booking.getClient());
        }


        Optional<Car> optionalCar = this.carRepository.findByImmatriculation(booking.getCar().getImmatriculation());
        Car car;
        if (optionalCar.isPresent()) {
            car = optionalCar.get();
        }
        else {
            booking.getCar().setClient(client);
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
        car.setClient(client);
        booking.setCar(car);
        booking.setParkingspot(parkingspot);
        booking.setClient(client);



        this.bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }

}
