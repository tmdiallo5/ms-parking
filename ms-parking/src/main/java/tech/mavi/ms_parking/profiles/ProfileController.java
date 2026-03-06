package tech.mavi.ms_parking.profiles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RequestMapping("profile")
@RestController
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Set<ProfileDTO> getAllProfiles() {
        return this.profileService.getAllprofiles();
    }


}
