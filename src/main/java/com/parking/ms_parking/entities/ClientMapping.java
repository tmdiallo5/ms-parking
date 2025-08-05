package com.parking.ms_parking.entities;

import org.springframework.stereotype.Component;

@Component
public class ClientMapping {
    public Client dtoToEntity(ClientDTO dto) {
        Client entity = new Client();
        entity.setPassword(dto.password());
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        return entity;
    }
}
