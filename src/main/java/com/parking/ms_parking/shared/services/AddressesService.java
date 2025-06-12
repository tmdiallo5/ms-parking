package com.parking.ms_parking.shared.services;

import com.parking.ms_parking.shared.entities.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class AddressesService {

    private AddressesRepository addressesRepository;

    public Address creat(Address address) {
        return  this.addressesRepository.save(address);

    }
}
