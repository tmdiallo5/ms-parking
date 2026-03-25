package tech.mavi.ms_parking.authentication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tech.mavi.ms_parking.profiles.Profile;
import tech.mavi.ms_parking.profiles.ProfileDTO;
import tech.mavi.ms_parking.security.token.JwtService;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RequestMapping(consumes = APPLICATION_JSON_VALUE)
@RestController
public class AuthentificationController {

    private final AuthentificationService authentificationService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "sign-up")
    public void create(@RequestBody ProfileDTO profileDTO){
        this.authentificationService.create(profileDTO);
    }


    @PostMapping(path = "sign-in")
    public @ResponseBody Map<String, String> login(@RequestBody Map<String, String> parameters){
        Authentication authentication = this.authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       parameters.get("email"),
                       parameters.get("password")
               )
        );
        String bearer = jwtService.generateToken(authentication);
        return Map.of("bearer", bearer);

    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "activate")
    public void activate(@RequestBody Map<String, String> parameters){
        this.authentificationService.activate(parameters);
    }
}
