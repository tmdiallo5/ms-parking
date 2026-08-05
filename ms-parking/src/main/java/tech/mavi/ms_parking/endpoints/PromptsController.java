package tech.mavi.ms_parking.endpoints;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping(path = "prompts")
public class PromptsController {

    private final PromptsService promptsService;

    @PostMapping(path = "chat")
    public ChatResponse chat(@RequestBody ChatRequest body) {
        return this.promptsService.chat(body);
    }
}
