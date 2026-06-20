package tech.mavi.ms_parking.reservations;

import tech.mavi.ms_parking.enums.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationResponseDto(
        int id,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        ReservationStatus reservationStatus,
        int spotId

) {

}

