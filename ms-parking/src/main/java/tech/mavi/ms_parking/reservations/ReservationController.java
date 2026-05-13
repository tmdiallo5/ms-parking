package tech.mavi.ms_parking.reservations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.mavi.ms_parking.spots.AvailableSpotRequestDto;
import tech.mavi.ms_parking.spots.AvailableSpotResponseDto;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RequestMapping(consumes = APPLICATION_JSON_VALUE)
@RestController
public class ReservationController {

    private final ReservationService reservationService;


    @PostMapping(path = "reservation")
    public ReservationResponseDto createReservation(@RequestBody ReservationRequestDto reservationDto) {
        return this.reservationService.createReservation(reservationDto);
    }

    @PostMapping(path = "available-spot")
    public List<AvailableSpotResponseDto> findAvailableSpot(@RequestBody AvailableSpotRequestDto availableSpotRequestDto) {
        return this.reservationService.findAvailableSpot(
                availableSpotRequestDto.addressId(),
                availableSpotRequestDto.from(),
                availableSpotRequestDto.until()
        );
    }

}
