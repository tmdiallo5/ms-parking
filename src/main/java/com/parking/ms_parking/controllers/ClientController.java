package com.parking.ms_parking.controllers;

import com.parking.ms_parking.entities.Client;
import com.parking.ms_parking.entities.ClientDTO;
import com.parking.ms_parking.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<ClientDTO> search() {
        return this.clientService.search();
    }
    @GetMapping(path = "{id}")
    public Client read(@PathVariable int id) {
            return this.clientService.read(id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}")
    public Client update(@PathVariable int id, @RequestBody Client client) {
        return this.clientService.update(id, client);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable int id) {
        this.clientService.delete(id);
    }

}
