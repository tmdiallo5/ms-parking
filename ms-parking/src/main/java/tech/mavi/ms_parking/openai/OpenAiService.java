package tech.mavi.ms_parking.openai;


import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;

import org.springframework.core.io.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.mavi.ms_parking.endpoints.ChatRequest;
import tech.mavi.ms_parking.endpoints.ChatResponse;


import java.util.Map;

@Component
public class OpenAiService {

    private final ParkingSearchStore parkingSearchStore;
    private final ParkingAssistantResult parkingAssistantResult;
    private final ParkingAssistantTools parkingAssistantTools;
    private final ChatClient chatClient;
    Resource chatSystem;

    public OpenAiService(ChatClient.Builder builder, ChatMemory chatMemory, ParkingSearchStore parkingSearchStore, ParkingAssistantTools parkingAssistantTools, ParkingAssistantResult parkingAssistantResult,
                         @Value("classpath:/prompts/chat-system.st") Resource chatSystem
    ) {
        this.chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
        this.parkingSearchStore = parkingSearchStore;
        this.parkingAssistantTools = parkingAssistantTools;
        this.parkingAssistantResult = parkingAssistantResult;
        this.chatSystem = chatSystem;
    }


    private Message generateMessage(Resource resource, Map<String, Object> params) {
     PromptTemplate promptTemplate = PromptTemplate
                .builder()
                .resource(resource)
                .build();
     return promptTemplate.createMessage(params);
    }

    public ChatResponse chat(ChatRequest chatRequest){

        ParkingSearchState state =
                parkingSearchStore.get(chatRequest.conversationId());

        Message systemMessage = this.generateMessage(chatSystem,Map.of(
                "location",
                state.location() == null ? "unknown" : state.location(),
                "date",
                state.date() == null ? "unknown" : state.date(),
                "startTime",
                state.startTime() == null ? "unknown" : state.startTime(),
                "endTime",
                state.endTime() == null ? "unknown" : state.endTime()
        ));

        String answer = this.chatClient
                .prompt()
                .system(systemMessage.getText())
                .user(chatRequest.message())
                .advisors(advisor -> advisor.param(
                        ChatMemory.CONVERSATION_ID,
                        chatRequest.conversationId()
                ))
                .tools(parkingAssistantTools)
                .call()
                .content();

        return new ChatResponse(
                answer,
                parkingAssistantResult.getAvailableSpots()
        );

    }
}
