package tech.mavi.ms_parking.reservations;

import tech.mavi.ms_parking.enums.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationDTO(
        int id,
        int parkingId,
        String parkingName,
        int spotId,
        String spotNumber,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        ReservationStatus reservationStatus,
        LocalDateTime cancelledAt,
        LocalDateTime createdAt

) {
}




