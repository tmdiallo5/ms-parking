package tech.mavi.ms_parking.openai;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import tech.mavi.ms_parking.spots.AvailableSpotResponseDto;

import java.util.List;

@Setter
@Getter
@Component
@RequestScope
public class ParkingAssistantResult {

    private List<AvailableSpotResponseDto> availableSpots = List.of();


}