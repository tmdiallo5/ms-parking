package com.parking.ms_parking.security.activations;

import com.parking.ms_parking.profiles.Profile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "activations")
public class Activation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Transient
    private int userCode;
    private String code;
    private Boolean active;
    private LocalDateTime creation;
    private LocalDateTime desactivation;

    @ManyToOne(cascade ={CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name="profile_id")
    private Profile profiles;
}
