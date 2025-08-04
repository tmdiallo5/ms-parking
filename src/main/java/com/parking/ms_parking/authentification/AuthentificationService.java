package com.parking.ms_parking.authentification;

import com.parking.ms_parking.entities.Client;
import com.parking.ms_parking.repository.ClientRepository;
import com.parking.ms_parking.shared.entities.Address;
import com.parking.ms_parking.shared.services.AddressesService;
import com.parking.ms_parking.shared.services.ValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class AuthentificationService {

    private final ClientRepository clientRepository;
    private final ValidationService validationService;
    private final BCryptPasswordEncoder passwordEncoder;


    public void create(Client client) {

        String userPassword = client.getPassword();
        String encodedPassword = passwordEncoder.encode(userPassword);
        client.setPassword(encodedPassword);

        this.validationService.validateEmail(client.getEmail());
        Optional<Client> clientDB = clientRepository.findByEmail(client.getEmail());
        if (clientDB.isPresent()) {
            throw new RuntimeException("client already exists");
        }

        this.clientRepository.save(client);
    }
}
