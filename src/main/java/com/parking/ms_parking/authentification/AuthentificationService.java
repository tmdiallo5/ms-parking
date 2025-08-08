package com.parking.ms_parking.authentification;

import com.parking.ms_parking.entities.Profile;
import com.parking.ms_parking.entities.ProfileDTO;
import com.parking.ms_parking.entities.ProfileMapper;
import com.parking.ms_parking.repository.ClientRepository;
import com.parking.ms_parking.shared.services.ValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class AuthentificationService {

    private final ClientRepository clientRepository;
    private final ValidationService validationService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfileMapper profileMapper;


    public void create(ProfileDTO profileDTO) {

        Optional<Profile> clientDB = clientRepository.findByEmail(profileDTO.email());
        if (clientDB.isPresent()) {
            throw new RuntimeException("client already exists");
        }

        String userPassword = profileDTO.password();
        Profile profile = this.profileMapper.dtoToEntity(profileDTO);
        String encodedPassword = passwordEncoder.encode(userPassword);
        profile.setPassword(encodedPassword);

        this.validationService.validateEmail(profile.getEmail());


        this.clientRepository.save(profile);
    }
}
