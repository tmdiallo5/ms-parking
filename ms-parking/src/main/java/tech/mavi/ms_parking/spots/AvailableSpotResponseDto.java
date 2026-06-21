package tech.mavi.ms_parking.spots;

import java.time.LocalDateTime;

public record AvailableSpotResponseDto(
        Integer spotId,
        String number,
        String spotType,
        String parkingName,
        double priceHour,
        double latitude,
        double longitude,
        String address,
        String imageUrl,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {
}

