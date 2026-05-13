package tech.mavi.ms_parking.shared.entities.address;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAllAddress(String keyword) {
        return this.addressRepository.findByStreetContainingIgnoreCaseOrCityContainingIgnoreCaseOrZipContainingIgnoreCase(keyword,keyword, keyword);
    }
}
