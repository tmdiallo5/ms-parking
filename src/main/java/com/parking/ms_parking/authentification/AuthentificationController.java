package com.parking.ms_parking.authentification;

import com.parking.ms_parking.profiles.ProfileDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
