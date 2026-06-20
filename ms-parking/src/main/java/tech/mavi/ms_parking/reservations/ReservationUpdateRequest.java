package tech.mavi.ms_parking.reservations;

import java.time.LocalDateTime;

public record ReservationUpdateRequest (
        int spotId,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
){
}
