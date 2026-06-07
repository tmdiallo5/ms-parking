package tech.mavi.ms_parking.reservations;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.mavi.ms_parking.enums.ReservationStatus;
import tech.mavi.ms_parking.profiles.Profile;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findBySpotIdInAndReservationStatusAndStartDateTimeBeforeAndEndDateTimeAfter(
            List<Integer> spotID,
            ReservationStatus reservationStatus,
            LocalDateTime until,
            LocalDateTime from
    );

    List<Reservation> findByProfile(Profile profile);

}
