package tech.mavi.ms_parking.reservations;

import java.time.LocalDateTime;

public record ReservationRequestDto(
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        Integer spotId

        ) {
}


