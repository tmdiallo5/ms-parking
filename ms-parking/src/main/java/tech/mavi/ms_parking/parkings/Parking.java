package tech.mavi.ms_parking.parkings;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.mavi.ms_parking.enums.ParkingStatus;
import tech.mavi.ms_parking.shared.entities.address.Address;
import tech.mavi.ms_parking.spots.Spot;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
      private ParkingStatus parkingStatus;
    private double pricePerHour;
    private double latitude;
    private double longitude;
    private String imageUrl;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "parking")
    private List<Spot> spots;
}
