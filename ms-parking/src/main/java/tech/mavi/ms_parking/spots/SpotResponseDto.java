package tech.mavi.ms_parking.spots;

import tech.mavi.ms_parking.enums.SpotStatus;
import tech.mavi.ms_parking.enums.SpotType;

public record SpotResponseDto(
        int spotId,
        String spotNumber,
        SpotType spotType,
        SpotStatus spotStatus

) {
}
