package tech.mavi.ms_parking.reservations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.mavi.ms_parking.enums.ReservationStatus;
import tech.mavi.ms_parking.parkings.Parking;
import tech.mavi.ms_parking.parkings.ParkingRepository;
import tech.mavi.ms_parking.profiles.Profile;
import tech.mavi.ms_parking.security.service.SecurityService;
import tech.mavi.ms_parking.spots.*;
import tech.mavi.ms_parking.spots.SpotResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@AllArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SecurityService securityService;
    private final SpotRepository spotRepository;
    private final ReservationMapper reservationMapper;
    private final ParkingRepository parkingRepository;
    private final SpotMapper spotMapper;





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
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());
        Reservation savedReservation = this.reservationRepository.save(reservation);

        return reservationMapper.toDto(savedReservation);
    }


    public List<AvailableSpotResponseDto> findAvailableSpot(int addressId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)){
            throw new RuntimeException("start and end are not valid");
        }

        List<AvailableSpotResponseDto> availableSpots = new ArrayList<>();

        List<Parking> parkings = this.parkingRepository.findByAddressId(addressId);

        for (Parking parking : parkings) {
           List<Spot> spots = parking.getSpots();
           List<Integer> spotIds = spots.stream().map(Spot::getId).toList();
           List<Reservation> blockedReservations = this.reservationRepository
                   .findBySpotIdInAndReservationStatusAndStartDateTimeBeforeAndEndDateTimeAfter(spotIds, ReservationStatus.CONFIRMED, startDateTime, endDateTime);
           List<Integer> blockedReservedSpotIds = blockedReservations.stream().map(Reservation::getId).toList();
           for (Spot spot : spots) {
               if (!blockedReservedSpotIds.contains(spot.getId())) {
                   AvailableSpotResponseDto dto = new AvailableSpotResponseDto(
                           spot.getId(),
                           spot.getNumber(),
                           spot.getSpotType().name(),
                           parking.getName(),
                           parking.getPricePerHour(),
                           parking.getLatitude(),
                           parking.getLongitude(),
                           parking.getAddress().getStreet() + "," +
                                   parking.getAddress().getZip() + "," +
                                   parking.getAddress().getCity(),
                           startDateTime,
                           endDateTime
                   );
                   availableSpots.add(dto);
               }
           }
        }

        return availableSpots;
    }

    public List<ReservationDTO> myReservations() {
       Profile currentProfile =  securityService.getCurrentUser();
       List<Reservation> reservations = reservationRepository.findByProfileIdOrderByUpdatedAtDesc(currentProfile.getId());
       LocalDateTime now = LocalDateTime.now();
       for (Reservation reservation : reservations) {
           if (reservation.getReservationStatus() == ReservationStatus.CONFIRMED
               && reservation.getEndDateTime().isBefore(now)) {
               reservation.setReservationStatus(ReservationStatus.COMPLETED);
           }
       }
       reservationRepository.saveAll(reservations);
       return reservations.stream()
               .map(reservationMapper::toReservationDto)
               .toList();
    }

    public ReservationResponseDto cancelReservation(int id) {
        Profile currentProfile = securityService.getCurrentUser();
        Reservation reservation = this.reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        if (!reservation.getProfile().getId().equals(currentProfile.getId())) {
            throw new RuntimeException("You are not allowed to cancel this reservation");
        }
        reservation.setReservationStatus(ReservationStatus.CANCELLED);
        reservation.setCancelledAt(LocalDateTime.now());
       Reservation reservationSaved = this.reservationRepository.save(reservation);
       return reservationMapper.toDto(reservationSaved);
    }

    public ReservationResponseDto reservationUpdate(int id, ReservationUpdateRequest reservationUpdateRequest) {
       Profile currentProfile = securityService.getCurrentUser();
       Reservation reservation = this.reservationRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (!reservation.getReservationStatus().equals(ReservationStatus.CONFIRMED)
                || !reservation.getProfile().getId().equals(currentProfile.getId())) {
            throw new RuntimeException("You can't update that reservation");
        }
        if (reservationUpdateRequest.startDateTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Reservation already started");
        }
        if (reservationUpdateRequest.startDateTime().isAfter(reservationUpdateRequest.endDateTime())
                || reservationUpdateRequest.startDateTime().isEqual(reservationUpdateRequest.endDateTime())) {
            throw new RuntimeException("You can't update that reservation. Start date must be before end date");
        }

       Spot spot = this.spotRepository.findById(reservationUpdateRequest.spotId())
               .orElseThrow(() -> new RuntimeException("Spot not found"));

       List<Reservation> blockedReservation =  this.reservationRepository.findBySpotIdInAndReservationStatusAndStartDateTimeBeforeAndEndDateTimeAfter(
                List.of(reservationUpdateRequest.spotId()),
                ReservationStatus.CONFIRMED,
                reservationUpdateRequest.endDateTime(),
                reservationUpdateRequest.startDateTime());
       for (Reservation reservationBlocked : blockedReservation) {
           if (!reservationBlocked.getId().equals(reservation.getId())) {
               throw new RuntimeException("Spot already reserved for this period");
           }
       }


       reservation.setSpot(spot);
       reservation.setStartDateTime(reservationUpdateRequest.startDateTime());
       reservation.setEndDateTime(reservationUpdateRequest.endDateTime());
       reservation.setUpdatedAt(LocalDateTime.now());
       Reservation reservationSaved = this.reservationRepository.save(reservation);
       return reservationMapper.toDto(reservationSaved);
    }

    public List<SpotResponseDto> availableSpotsByParking(
            int parkingId, LocalDateTime startDateTime, LocalDateTime endDateTime, int currentReservationId
    ) {
        Parking parking = this.parkingRepository.findById(parkingId)
                .orElseThrow(() -> new RuntimeException("Parking not found"));
        List<Spot> spots = parking.getSpots();
        List<Integer> spotsIds = spots.stream().map(Spot::getId).toList();
      List<Reservation> blockedReservation = this.reservationRepository
              .findBySpotIdInAndReservationStatusAndStartDateTimeBeforeAndEndDateTimeAfter(
               spotsIds,
               ReservationStatus.CONFIRMED,
               endDateTime,
               startDateTime
       );
     List<Integer> blockedSpotIds = blockedReservation.stream()
             .filter(reservation -> !reservation.getId().equals(currentReservationId))
             .map(reservation -> reservation.getSpot().getId())
             .toList();
    List<Spot> availableSpots = spots.stream().
            filter(spot -> !blockedSpotIds.contains(spot.getId()))
            .toList();

    return availableSpots
            .stream().map(spotMapper::toSpotResponseDto)
            .toList();

    }
}


