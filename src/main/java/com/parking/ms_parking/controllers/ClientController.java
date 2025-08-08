package com.parking.ms_parking.controllers;

import com.parking.ms_parking.entities.Profile;
import com.parking.ms_parking.entities.ProfileDTO;
import com.parking.ms_parking.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<ProfileDTO> search() {
        return this.clientService.search();
    }
    @GetMapping(path = "{id}")
    public Profile read(@PathVariable int id) {
            return this.clientService.read(id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}")
    public Profile update(@PathVariable int id, @RequestBody Profile profile) {
        return this.clientService.update(id, profile);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable int id) {
        this.clientService.delete(id);
    }

}
