package tech.mavi.ms_parking.spots;

import org.springframework.stereotype.Component;

@Component
public class SpotMapper {
    public SpotResponseDto toSpotResponseDto(Spot spot) {
        return new SpotResponseDto(
                spot.getId(),
                spot.getNumber(),
                spot.getSpotType(),
                spot.getSpotStatus()
        );
    }
}
