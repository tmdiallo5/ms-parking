package com.parking.ms_parking.parkingspot;

import com.parking.ms_parking.parking.Parking;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("parkingspot")
public class PrkingSpotController {

    private final ParkingSpotService parkingSpotService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "{parkingId}")
    public void createParkingSpot(@PathVariable int parkingId, @RequestBody  List<Parkingspot> parkingspot) {
        this.parkingSpotService.createParkingSpot(parkingId,parkingspot);
    }
}
