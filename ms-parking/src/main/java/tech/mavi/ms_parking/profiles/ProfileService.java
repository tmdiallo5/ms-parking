package tech.mavi.ms_parking.profiles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public Set<ProfileDTO> getAllprofiles() {
        List<Profile> profiles =  profileRepository.findAll();
      return   profiles.stream().map(this.profileMapper::entityToDto).collect(Collectors.toSet());
    }
}
