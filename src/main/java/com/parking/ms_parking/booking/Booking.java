package com.parking.ms_parking.booking;

import com.parking.ms_parking.car.Car;
import com.parking.ms_parking.parkingspot.Parkingspot;
import com.parking.ms_parking.profiles.Profile;


import com.parking.ms_parking.status.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "bookings_status", joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private Set<Status> statuses = new HashSet<>();
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "parkingspot_id")
    private Parkingspot parkingspot;

}
