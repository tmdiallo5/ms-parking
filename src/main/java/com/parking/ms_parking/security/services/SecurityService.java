package com.parking.ms_parking.security.services;

import com.parking.ms_parking.profiles.Profile;
import com.parking.ms_parking.profiles.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@AllArgsConstructor
@Component
public class SecurityService {

    private ProfileRepository profileRepository;

    public Profile getCurrentProfile() {
        Jwt jwt = (Jwt) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String email = jwt.getSubject();
        Optional<Profile> profile =  this.profileRepository.findByEmail(email);
        return profile.orElseThrow(() -> new RuntimeException("connection error."));
    }

}
