package com.example.AI.AIspire.Services.Impl;

import com.example.AI.AIspire.Services.OllamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OllamaServiceImpl implements OllamaService {

    private ChatClient chatClient;

    @Autowired
    public OllamaServiceImpl(OllamaChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    private static final String OLLAMA_URL = "http://localhost:11434/v1/chat/completions";

    public String getStepsFromAi(String goal, String date, String target) {
        String prompt = "You are an expert personal coach.\n" +
                "\n" +
                "Break the following goal into a clear, step-by-step to-do list of everything a beginner must do to achieve the final outcome. Each step should build upon the previous one, progressing from basic to advanced.\n" +
                "\n" +
                "Goal Input:\n" +
                "{\n" +
                "\"title\":"+goal+"\n" +
                "\"description\": \""+target+"\",\n" +
                "\"targetDate\": \""+date+"\"\n" +
                "}\n" +
                "\n" +
                "Output Format:\n" +
                "Provide a numbered list of steps (1, 2, 3, … n)\n" +
                "\n" +
                "Each step should be one short actionable sentence\n" +
                "\n" +
                "At the end of each step, add a due date in yyyy-mm-dd format\n" +
                "\n" +
                "Start from beginner-friendly basics\n" +
                "\n" +
                "Progress logically toward mastering the goal\n" +
                "\n" +
                "End with high-level mastery as described in the \"description\"\n" +
                "\n" +
                "Include appropriate durations between steps to allow real progress\n" +
                "\n" +
                "Ensure final step completes on or before"+date+ "\n" +
                "\n" +
                "Only output the list, no extra explanation or headings";

        return chatClient.prompt(prompt).call().content();
    }
}



//        Map<String, Object> response = webClient.post()
//                .uri(OLLAMA_URL)
//                .bodyValue(Map.of(
//                        "model", "llama3",
//                        "messages", List.of(
//                                Map.of("role", "user", "content", prompt)
//                        )
//                ))
//                .retrieve()
//                .bodyToMono(Map.class)
//                .block();
//
//        // Extract content from response
//        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
//        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
//        return (String) message.get("content");}

