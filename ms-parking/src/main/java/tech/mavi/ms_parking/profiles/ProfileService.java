package tech.mavi.ms_parking.profiles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.mavi.ms_parking.security.service.SecurityService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final SecurityService securityService;

    public Set<ProfileDTO> getAllprofiles() {
        List<Profile> profiles =  profileRepository.findAll();
      return   profiles.stream().map(this.profileMapper::entityToDto).collect(Collectors.toSet());
    }

    public Profile readProfileById(int id) {
        Optional<Profile> profileOptional = profileRepository.findById(id);
        return profileOptional.orElse(null);
    }

    public void deleteProfile(int id) {
        Profile profile = readProfileById(id);
        profileRepository.delete(profile);
    }


    public ProfileDTO getCurrentUser() {
      Profile profile =   this.securityService.getCurrentUser();
      return this.profileMapper.entityToDto(profile);
    }
}
