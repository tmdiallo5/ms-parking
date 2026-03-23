package tech.mavi.ms_parking.security.activations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivationsRepository extends JpaRepository<Activation, Integer> {
    List<Activation> findByActiveAndDesactivationAfter(boolean active, LocalDateTime desactivation);
}
