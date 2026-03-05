package tech.mavi.ms_parking.profiles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@AllArgsConstructor
@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public void create(Profile profile) {

        String userPassword = profile.getPassword();
        String encodedPassword = passwordEncoder.encode(userPassword);
        profile.setPassword(encodedPassword);

        this.profileRepository.save(profile);
    }

}
