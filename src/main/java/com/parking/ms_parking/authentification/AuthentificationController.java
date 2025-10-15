package com.parking.ms_parking.authentification;

import com.parking.ms_parking.profiles.ProfileDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@AllArgsConstructor
@RequestMapping(consumes = APPLICATION_JSON_VALUE)
@RestController
public class AuthentificationController {

    private final AuthentificationService authentificationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "sign-up")
    public void create(@RequestBody ProfileDTO profileDTO) {
        this.authentificationService.create(profileDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "sign-in")
    public void login(@RequestBody Map<String, String> parameters) {

    }


    @PostMapping(path = "activate")
    public void activate(@RequestBody Map<String, String> parameters) {
        this.authentificationService.activate(parameters);
    }
}
