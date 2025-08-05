package com.parking.ms_parking.services;

import com.parking.ms_parking.entities.Client;
import com.parking.ms_parking.entities.ClientDTO;
import com.parking.ms_parking.entities.ClientMapper;
import com.parking.ms_parking.repository.ClientRepository;
import com.parking.ms_parking.shared.entities.Address;
import com.parking.ms_parking.shared.services.AddressesService;
import com.parking.ms_parking.shared.services.ValidationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClientService {


    private final ClientRepository clientRepository;
    private final ClientMapper clientmapper;



    public Set<ClientDTO> search() {
        List<Client> clients = this.clientRepository.findAll();
        return clients.stream().map(this.clientmapper::entityToDto).collect(Collectors.toSet());

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
