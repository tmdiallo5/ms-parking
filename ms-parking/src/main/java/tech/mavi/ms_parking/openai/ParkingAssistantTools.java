package tech.mavi.ms_parking.openai;

import lombok.AllArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import tech.mavi.ms_parking.reservations.ReservationDTO;
import tech.mavi.ms_parking.reservations.ReservationService;

import tech.mavi.ms_parking.spots.AvailableSpotResponseDto;


import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Component
public class ParkingAssistantTools {

    private final ReservationService reservationService;
    private final ParkingAssistantResult parkingAssistantResult;

    @Tool(description =  """
            Searches for available parking spaces at a specified location within a given time range.
             Use this tool only when the user explicitly requests a search for available parking spaces.
            """)
    public List<AvailableSpotResponseDto> findAvailbleSpotsAssist(
            @ToolParam(description = "City, street, or postal code")
            String location,
            @ToolParam(description = "Start in the format yyyy-MM-dd'T'HH:mm:ss")
            String startDateTime,
            @ToolParam(description = "End in the format yyyy-MM-dd'T'HH:mm:ss")
            String endDateTime)
    {

            List<AvailableSpotResponseDto> spots = reservationService.findAvailableSpotByLocation(
                    location,
                    LocalDateTime.parse(startDateTime),
                    LocalDateTime.parse(endDateTime)
            );

            parkingAssistantResult.setAvailableSpots(spots);
            return spots;

    }
    @Tool(description = """  
            Returns all reservations for the currently authenticated user.
            Use this tool to answer questions about the user's total number of reservations,
            including active, completed, and cancelled reservations.
    """)
    public List<ReservationDTO> getMyReservations() {
        return reservationService.myReservations();
    }
}
