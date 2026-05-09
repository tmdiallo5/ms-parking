package tech.mavi.ms_parking.reservations;

import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public Reservation toEntity(ReservationRequestDto dto) {
       Reservation reservation = new Reservation();

       reservation.setStartDateTime(dto.startDateTime());
       reservation.setEndDateTime(dto.endDateTime());

       return reservation;
    }

    public ReservationResponseDto toDto(Reservation reservation) {
        return new ReservationResponseDto(
                reservation.getId(),
                reservation.getStartDateTime(),
                reservation.getEndDateTime(),
                reservation.getReservationStatus(),
                reservation.getSpot().getId()
        );
    }
}
