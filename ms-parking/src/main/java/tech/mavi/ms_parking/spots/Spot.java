package tech.mavi.ms_parking.spots;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.mavi.ms_parking.enums.SpotStatus;
import tech.mavi.ms_parking.enums.SpotType;
import tech.mavi.ms_parking.parkings.Parking;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "spot")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    @Enumerated(EnumType.STRING)
    private SpotStatus spotStatus;
    @Enumerated(EnumType.STRING)
    private SpotType spotType;
    @ManyToOne
    private Parking parking;


}
