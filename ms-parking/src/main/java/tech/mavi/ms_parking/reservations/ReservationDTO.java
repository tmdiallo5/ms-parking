package tech.mavi.ms_parking.reservations;

import tech.mavi.ms_parking.enums.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationDTO(
        int id,
        String parkingName,
        String spotNumber,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        ReservationStatus reservationStatus,
        LocalDateTime cancelledAt

) {
}


