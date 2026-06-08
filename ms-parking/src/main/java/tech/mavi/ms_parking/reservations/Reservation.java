package tech.mavi.ms_parking.reservations;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.mavi.ms_parking.enums.ReservationStatus;
import tech.mavi.ms_parking.profiles.Profile;
import tech.mavi.ms_parking.spots.Spot;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
    private LocalDateTime cancelledAt;
    private LocalDateTime createdAt;
    @ManyToOne
    private Profile profile;
    @ManyToOne
    private Spot spot;

}
