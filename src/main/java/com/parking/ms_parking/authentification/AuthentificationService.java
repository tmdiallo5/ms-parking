package com.parking.ms_parking.authentification;

import com.parking.ms_parking.entities.Client;
import com.parking.ms_parking.entities.ClientDTO;
import com.parking.ms_parking.entities.ClientMapping;
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
    private final ClientMapping clientMapping;


    public void create(ClientDTO clientDTO) {

        Optional<Client> clientDB = clientRepository.findByEmail(clientDTO.email());
        if (clientDB.isPresent()) {
            throw new RuntimeException("client already exists");
        }

        String userPassword = clientDTO.password();
        Client client = this.clientMapping.dtoToEntity(clientDTO);
        String encodedPassword = passwordEncoder.encode(userPassword);
        client.setPassword(encodedPassword);

        this.validationService.validateEmail(client.getEmail());


        this.clientRepository.save(client);
    }
}
