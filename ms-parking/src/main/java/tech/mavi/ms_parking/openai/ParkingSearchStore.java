package tech.mavi.ms_parking.openai;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ParkingSearchStore {

    private final Map<String, ParkingSearchState> conversations =
            new ConcurrentHashMap<>();

    public ParkingSearchState get(String conversationId) {
        return conversations.getOrDefault(
                conversationId,
                new ParkingSearchState(null, null, null, null)
        );
    }

    public void save(
            String conversationId,
            ParkingSearchState state
    ) {
        conversations.put(conversationId, state);
    }

    public void clear(String conversationId) {
        conversations.remove(conversationId);
    }
}