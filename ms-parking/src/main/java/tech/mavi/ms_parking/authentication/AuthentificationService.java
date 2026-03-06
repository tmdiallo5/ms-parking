package tech.mavi.ms_parking.authentication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.mavi.ms_parking.profiles.Profile;
import tech.mavi.ms_parking.profiles.ProfileDTO;
import tech.mavi.ms_parking.profiles.ProfileMapper;
import tech.mavi.ms_parking.profiles.ProfileRepository;

@Slf4j
@AllArgsConstructor
@Service
public class AuthentificationService {

    private final ProfileMapper profileMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;


    public void create(ProfileDTO profileDTO) {
        Profile profile = this.profileMapper.dtoToEntity(profileDTO);
        String userPassword = profileDTO.password();
        String encodedPassword = passwordEncoder.encode(userPassword);
        profile.setPassword(encodedPassword);

        this.profileRepository.save(profile);
    }
}
