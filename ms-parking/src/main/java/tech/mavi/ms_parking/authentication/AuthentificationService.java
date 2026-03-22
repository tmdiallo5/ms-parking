package tech.mavi.ms_parking.authentication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.mavi.ms_parking.profiles.*;

@Slf4j
@AllArgsConstructor
@Service
public class AuthentificationService {

    private final ProfileMapper profileMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;
    private final RolesRepository rolesRepository;


    public void create(ProfileDTO profileDTO) {
        Profile profile = this.profileMapper.dtoToEntity(profileDTO);
        String userPassword = profileDTO.password();
        String encodedPassword = passwordEncoder.encode(userPassword);
        profile.setPassword(encodedPassword);
        Role role = this.rolesRepository.findByName("CLIENT");
        profile.setRole(role);

        this.profileRepository.save(profile);
    }
}
