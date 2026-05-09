package tech.mavi.ms_parking.reservations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.mavi.ms_parking.enums.ReservationStatus;
import tech.mavi.ms_parking.profiles.Profile;
import tech.mavi.ms_parking.security.service.SecurityService;
import tech.mavi.ms_parking.spots.Spot;
import tech.mavi.ms_parking.spots.SpotRepository;

@Slf4j
@AllArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SecurityService securityService;
    private final SpotRepository spotRepository;
    private ReservationMapper reservationMapper;


    public boolean checkReservationPossible(Reservation reservation) {
        if (reservation.getStartDateTime().isAfter(reservation.getEndDateTime())) {
            return false;
        }
        if (reservation.getStartDateTime().isEqual(reservation.getEndDateTime())) {
            return false;
        }
        for (Reservation existingReservation  : reservationRepository.findAll()) {
            if (existingReservation.getSpot().getId() == reservation.getSpot().getId()) {
                if (existingReservation.getStartDateTime().isBefore(reservation.getEndDateTime())
                   && existingReservation.getEndDateTime().isAfter(reservation.getStartDateTime())
                ) {
                     return false;
                }
            }

        }

      return true;
    }

    public ReservationResponseDto createReservation(ReservationRequestDto reservationDto) {
        Profile currentProfile = securityService.getCurrentUser();

        Spot spot = spotRepository.findById(reservationDto.spotId())
                .orElseThrow(() -> new RuntimeException("Spot not found"));

        Reservation reservation = reservationMapper.toEntity(reservationDto);

        reservation.setProfile(currentProfile);
        reservation.setSpot(spot);
        reservation.setReservationStatus(ReservationStatus.CONFIRMED);

        if (!checkReservationPossible(reservation)) {
            throw new RuntimeException("Reservation is not possible");
        }
        Reservation savedReservation = this.reservationRepository.save(reservation);

        return reservationMapper.toDto(savedReservation);
    }
}
