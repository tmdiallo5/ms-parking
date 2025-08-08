package com.parking.ms_parking.services;

import com.parking.ms_parking.entities.Profile;
import com.parking.ms_parking.entities.ProfileDTO;
import com.parking.ms_parking.entities.ProfileMapper;
import com.parking.ms_parking.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClientService {


    private final ClientRepository clientRepository;
    private final ProfileMapper clientmapper;



    public Set<ProfileDTO> search() {
        List<Profile> profiles = this.clientRepository.findAll();
        return profiles.stream().map(this.clientmapper::entityToDto).collect(Collectors.toSet());

    }

    public Profile read(int id) {
        Optional<Profile> client =  this.clientRepository.findById(id);
        return client.orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public Profile update(int id, Profile profile) {
        Profile profileDataBase = this.read(id);
        profileDataBase.setFirstName(profile.getFirstName());
        profileDataBase.setLastName(profile.getLastName());
        profileDataBase.setEmail(profile.getEmail());
        profileDataBase.setPassword(profile.getPassword());

        profileDataBase = this.clientRepository.save(profileDataBase);
        return profileDataBase;
    }

    public void delete(int id) {
        this.clientRepository.deleteById(id);
    }
}
