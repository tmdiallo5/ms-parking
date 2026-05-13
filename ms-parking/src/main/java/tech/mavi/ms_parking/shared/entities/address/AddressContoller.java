package tech.mavi.ms_parking.shared.entities.address;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RestController
public class AddressContoller {

    private final AddressService addressService;

    @GetMapping(path = "search-address")
    public List<Address> getAllAddress(@RequestParam String keyword) {
        return this.addressService.getAllAddress(keyword);
    }
}
