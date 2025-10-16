package com.parking.ms_parking.authentification;

import com.parking.ms_parking.profiles.ProfileDTO;
import com.parking.ms_parking.security.token.JWTService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@Slf4j
@AllArgsConstructor
@RequestMapping(consumes = APPLICATION_JSON_VALUE)
@RestController
public class AuthentificationController {

    private final AuthentificationService authentificationService;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "sign-up")
    public void create(@RequestBody ProfileDTO profileDTO) {
        this.authentificationService.create(profileDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "sign-in")
    public @ResponseBody Map<String, String> login(@RequestBody Map<String, String> parameters) {
             Authentication authenticattion =  this.authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                parameters.get("email"),
                                parameters.get("password"))
                );
             String bearer = jwtService.generate(authenticattion);
             return Map.of("bearer", bearer);
    }


    @PostMapping(path = "activate")
    public void activate(@RequestBody Map<String, String> parameters) {
        this.authentificationService.activate(parameters);
    }
}
