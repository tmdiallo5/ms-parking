package com.parking.ms_parking.services;

import com.parking.ms_parking.entities.Client;
import com.parking.ms_parking.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class ClientService {

    private ClientRepository clientRepository;


    public void create(Client client) {
        this.clientRepository.save(client);
    }
}
