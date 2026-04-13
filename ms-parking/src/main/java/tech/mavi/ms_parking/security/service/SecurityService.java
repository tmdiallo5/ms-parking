package tech.mavi.ms_parking.security.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import tech.mavi.ms_parking.profiles.Profile;
import tech.mavi.ms_parking.profiles.ProfileRepository;

import java.util.Optional;

@AllArgsConstructor
@Component
public class SecurityService {

    private final ProfileRepository profileRepository;

    public Profile getCurrentUser(){

             Jwt jwt = (Jwt)  SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
             String email = jwt.getSubject();
        Optional<Profile> profileOptional= this.profileRepository.findByEmail(email);
        return profileOptional.orElseThrow(() -> new RuntimeException("User Not Found"));

    }
}
