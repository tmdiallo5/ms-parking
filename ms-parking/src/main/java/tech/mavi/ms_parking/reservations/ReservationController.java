package tech.mavi.ms_parking.reservations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.mavi.ms_parking.spots.AvailableSpotRequestDto;
import tech.mavi.ms_parking.spots.AvailableSpotResponseDto;


import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RequestMapping
@RestController
public class ReservationController {

    private final ReservationService reservationService;


    @PostMapping(path = "reservation", consumes = APPLICATION_JSON_VALUE )
    public ReservationResponseDto createReservation(@RequestBody ReservationRequestDto reservationDto) {
        return this.reservationService.createReservation(reservationDto);
    }

    @PostMapping(path = "available-spot", consumes = APPLICATION_JSON_VALUE)
    public List<AvailableSpotResponseDto> findAvailableSpot(@RequestBody AvailableSpotRequestDto availableSpotRequestDto) {
        return this.reservationService.findAvailableSpot(
                availableSpotRequestDto.addressId(),
                availableSpotRequestDto.from(),
                availableSpotRequestDto.until()
        );
    }

    @GetMapping(path = "my-reservations", produces = APPLICATION_JSON_VALUE)
    public Set<ReservationDTO> myReservations(){
        return this.reservationService.myReservations();
    }

}
