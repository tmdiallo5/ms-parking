package tech.mavi.ms_parking.security.activations;

import jakarta.persistence.*;
import lombok.*;
import tech.mavi.ms_parking.profiles.Profile;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activations")
public class Activation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Transient
    private int userCode;
    private String code;
    private boolean active;
    private LocalDateTime creation;
    private LocalDateTime desactivation;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
