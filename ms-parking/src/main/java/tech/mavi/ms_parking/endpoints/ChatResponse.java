package tech.mavi.ms_parking.endpoints;

import tech.mavi.ms_parking.spots.AvailableSpotResponseDto;

import java.util.List;

public record ChatResponse(
        String message,
        List<AvailableSpotResponseDto> availableSpots

) {
}
