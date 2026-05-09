package tech.mavi.ms_parking.spots;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpotRepository extends JpaRepository<Spot, Integer> {

}
