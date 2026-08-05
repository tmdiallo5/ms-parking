package tech.mavi.ms_parking.openai;

public record ParkingSearchState(
        String location,
        String date,
        String startTime,
        String endTime
) {
}