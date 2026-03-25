package tech.mavi.ms_parking.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile deleteById(int id);

    Optional<Profile>findByEmail(String username);
}
