package tech.mavi.ms_parking.reservations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RequestMapping("reservation")
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ReservationResponseDto createReservation(@RequestBody ReservationRequestDto reservationDto) {
        return this.reservationService.createReservation(reservationDto);
    }
}
