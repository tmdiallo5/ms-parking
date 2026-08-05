package tech.mavi.ms_parking.endpoints;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import tech.mavi.ms_parking.openai.OpenAiService;


@AllArgsConstructor
@Service
public class PromptsService {

    private final OpenAiService openAiService;

    public ChatResponse chat(ChatRequest chatRequest) {
        return this.openAiService.chat(chatRequest);
    }
}
