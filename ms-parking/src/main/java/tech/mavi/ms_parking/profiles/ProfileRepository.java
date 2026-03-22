package tech.mavi.ms_parking.profiles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile deleteById(int id);
}
