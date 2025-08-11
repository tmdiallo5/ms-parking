package com.parking.ms_parking.authentification;

import com.parking.ms_parking.notifications.EmailsService;
import com.parking.ms_parking.profiles.*;
import com.parking.ms_parking.security.activations.Activation;
import com.parking.ms_parking.security.activations.ActivationsService;
import com.parking.ms_parking.shared.services.ValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class AuthentificationService {

    private final ProfileRepository profileRepository;
    private final ValidationService validationService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfileMapper profileMapper;
    private final RolesRepository rolesRepository;
    private final ActivationsService activationsService;
    private final EmailsService emailsService;

    public void create(ProfileDTO profileDTO) {

        Optional<Profile> clientDB = profileRepository.findByEmail(profileDTO.email());
        if (clientDB.isPresent()) {
            throw new RuntimeException("client already exists");
        }

        String userPassword = profileDTO.password();
        Profile profile = this.profileMapper.dtoToEntity(profileDTO);
        String encodedPassword = passwordEncoder.encode(userPassword);
        profile.setPassword(encodedPassword);

        Role role = this.rolesRepository.findByName("CLIENT");
        profile.setRole(role);
        this.validationService.validateEmail(profile.getEmail());


        profile = this.profileRepository.save(profile);
        Activation activation = this.activationsService.create(profile);
        log.info("The activation code for the {} is: {} ", profile.getEmail(), activation.getUserCode());
        this.emailsService.send(
                Map.of(
                        "email", profile.getEmail(),
                        "name", String.format("%S %S", profile.getFirstName(), profile.getLastName()),
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
