package com.parking.ms_parking.entities;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client dtoToEntity(ClientDTO dto) {
        Client entity = new Client();
        entity.setPassword(dto.password());
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        return entity;
    }

    public ClientDTO entityToDto(Client entity) {
        return new ClientDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                null
        );
    }
}
