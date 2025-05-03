package co.ucentral.creditaplication.services;

import co.ucentral.creditaplication.dtos.MessageDto;
import co.ucentral.creditaplication.factories.BedrockClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class BedrockClaudeService {

    private static final String MODEL_ID = "arn:aws:bedrock:us-east-1:665450376256:inference-profile/us.anthropic.claude-3-5-haiku-20241022-v1:0";
    private final BedrockRuntimeClient bedrockClient;
    private String PROMPT_TEMPLATE = "";

    public BedrockClaudeService(BedrockClientFactory factory) {
        this.bedrockClient = factory.createClient();
    }

    public String callClaudeWithHistory(List<MessageDto> history, String userInput) throws Exception {
        List<Map<String, Object>> messages = new ArrayList<>();

        // Claude ahora usa el rol "system" en lugar de "context"
        messages.add(Map.of(
                "role", "user",
                "content", List.of(Map.of("type", "text", "text", getPromptTemplateForChat()))
        ));

        // Historial previo
        for (MessageDto message : history) {
            String role = message.getRole().equalsIgnoreCase("user") ? "user" : "assistant";

            messages.add(Map.of(
                    "role", role,
                    "content", List.of(Map.of("type", "text", "text", message.getContent()))
            ));
        }

        // Entrada del usuario actual
        messages.add(Map.of(
                "role", "user",
                "content", List.of(Map.of("type", "text", "text", userInput))
        ));

        // Payload para el request
        Map<String, Object> body = new HashMap<>();
        body.put("anthropic_version", "bedrock-2023-05-31");
        body.put("max_tokens", 800); // ajustable según tu presupuesto
        body.put("temperature", 0.7); // valor más neutro
        body.put("top_p", 0.9);
        body.put("top_k", 250);
        body.put("stop_sequences", Collections.emptyList());
        body.put("messages", messages);

        String jsonBody = new ObjectMapper().writeValueAsString(body);

        // Invocación del modelo
        InvokeModelRequest request = InvokeModelRequest.builder()
                .modelId(MODEL_ID) // ID del modelo sin ARN, si no usas inference profiles
                .contentType("application/json")
                .accept("application/json")
                .body(SdkBytes.fromString(jsonBody, StandardCharsets.UTF_8))
                .build();

        InvokeModelResponse response = bedrockClient.invokeModel(request);
        String responseBody = response.body().asUtf8String();

        // Parsing del resultado
        Map<String, Object> resultMap = new ObjectMapper().readValue(responseBody, Map.class);
        List<Map<String, Object>> outputs = (List<Map<String, Object>>) resultMap.get("content");
        return outputs.get(0).get("text").toString();
    }

    private String getPromptTemplateForChat() throws IOException {
        Path promptPath = new ClassPathResource("prompts/interviewer_prompt.txt").getFile().toPath();
        PROMPT_TEMPLATE = Files.readString(promptPath, StandardCharsets.UTF_8);
        return PROMPT_TEMPLATE;
    }
}
