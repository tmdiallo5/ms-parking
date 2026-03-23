package tech.mavi.ms_parking.authentication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.mavi.ms_parking.notifications.EmailsServce;
import tech.mavi.ms_parking.profiles.*;
import tech.mavi.ms_parking.security.activations.Activation;
import tech.mavi.ms_parking.security.activations.ActivationsService;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class AuthentificationService {

    private final ProfileMapper profileMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;
    private final RolesRepository rolesRepository;
    private final EmailsServce emailsServce;
    private final ActivationsService activationsService;


    public void create(ProfileDTO profileDTO) {
        Profile profile = this.profileMapper.dtoToEntity(profileDTO);
        String userPassword = profileDTO.password();
        String encodedPassword = passwordEncoder.encode(userPassword);
        profile.setPassword(encodedPassword);
        Role role = this.rolesRepository.findByName("CLIENT");
        profile.setRole(role);

        profile = this.profileRepository.save(profile);
        Activation activation = this.activationsService.create(profile);

        this.emailsServce.send(
                Map.of(
                        "email", profile.getEmail(),
                        "name", String.format("%s %s", profile.getFirstName(), profile.getLastName()),
                        "code", "" + activation.getUserCode(),
                        "template", "activation-code.ftl"
                )
        );
    }

    public void activate(Map<String, String> parameters) {
      Profile profile = this.activationsService.validateAndReturnProfile(parameters);
      profile.setActive(true);
      this.profileRepository.save(profile);
    }
}
