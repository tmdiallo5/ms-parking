package tech.mavi.ms_parking.spots;

import java.time.LocalDateTime;

public record AvailableSpotRequestDto(
        String address,
        LocalDateTime from,
        LocalDateTime until

) {
}
