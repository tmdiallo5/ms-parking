package tech.mavi.ms_parking.profiles;

import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public Profile dtoToEntity(ProfileDTO profileDTO) {
            Profile entity = new Profile();
            entity.setPassword(profileDTO.password());
            entity.setFirstName(profileDTO.firstName());
            entity.setLastName(profileDTO.lastName());
            entity.setEmail(profileDTO.email());
            return entity;
    }

    public ProfileDTO entityToDto(Profile entity) {
        return new ProfileDTO(entity.getFirstName(),  entity.getLastName(), entity.getEmail(), null);
    }
}
