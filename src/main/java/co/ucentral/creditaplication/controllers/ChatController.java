package co.ucentral.creditaplication.controllers;

import co.ucentral.creditaplication.dtos.MessageDto;
import co.ucentral.creditaplication.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired private SessionService sessionService;
    @Autowired private BedrockClaudeService bedrockService;

    @PostMapping("/{sessionId}")
    public Map<String, String> chat(
            @PathVariable String sessionId,
            @RequestBody Map<String, String> payload) throws Exception {

        String userMessage = payload.get("message");

        // Guardar mensaje del usuario
        sessionService.addMessage(sessionId, new MessageDto("usuario", userMessage));

        // Obtener historial + respuesta
        List<MessageDto> history = sessionService.getSessionHistory(sessionId);
        String aiResponse = bedrockService.callClaudeWithHistory(history, userMessage);

        // Guardar respuesta del asistente
        sessionService.addMessage(sessionId, new MessageDto("entrevistador", aiResponse));

        return Map.of("response", aiResponse);
    }
}
