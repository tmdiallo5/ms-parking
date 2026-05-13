package tech.mavi.ms_parking.spots;

import java.time.LocalDateTime;

public record AvailableSpotRequestDto(
        Long addressId,
        LocalDateTime from,
        LocalDateTime until

) {
}
