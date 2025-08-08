package com.parking.ms_parking.entities;

import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
    public Profile dtoToEntity(ProfileDTO dto) {
        Profile entity = new Profile();
        entity.setPassword(dto.password());
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        return entity;
    }

    public ProfileDTO entityToDto(Profile entity) {
        return new ProfileDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                null
        );
    }
}
