package com.parking.ms_parking.services;

import com.parking.ms_parking.entities.Client;
import com.parking.ms_parking.repository.ClientRepository;
import com.parking.ms_parking.shared.entities.Address;
import com.parking.ms_parking.shared.services.AddressesService;
import com.parking.ms_parking.shared.services.ValidationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientService {

    private final AddressesService addressesService;
    private final ClientRepository clientRepository;
    private final ValidationService validationService;


    public void create(Client client) {
        if(client.getAddress() != null) {
            Address address = this.addressesService.creat(client.getAddress());
            client.setAddress(address);
        }
        this.validationService.validateEmail(client.getEmail());
        Optional <Client> clientDB = clientRepository.findByEmail(client.getEmail());
        if (clientDB.isPresent()) {
           throw new RuntimeException("client already exists");
        }

        this.clientRepository.save(client);
    }

    public List<Client> search() {
        return this.clientRepository.findAll();
    }

    public Client read(int id) {
        Optional<Client> client =  this.clientRepository.findById(id);
        return client.orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public Client update(int id, Client client) {
        Client clientDataBase = this.read(id);
        clientDataBase.setFirstName(client.getFirstName());
        clientDataBase.setLastName(client.getLastName());
        clientDataBase.setEmail(client.getEmail());
        clientDataBase.setPassword(client.getPassword());

        clientDataBase = this.clientRepository.save(clientDataBase);
        return clientDataBase;
    }

    public void delete(int id) {
        this.clientRepository.deleteById(id);
    }
}
