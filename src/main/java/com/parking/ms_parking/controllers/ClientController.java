package com.parking.ms_parking.controllers;

import com.parking.ms_parking.entities.Client;
import com.parking.ms_parking.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public void create(@RequestBody Client client) {
        this.clientService.create(client);
    }
}
