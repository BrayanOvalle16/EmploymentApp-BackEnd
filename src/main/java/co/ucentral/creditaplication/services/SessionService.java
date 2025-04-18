package co.ucentral.creditaplication.services;

import co.ucentral.creditaplication.dtos.MessageDto;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SessionService {
    private final Map<String, List<MessageDto>> sessionMap = new HashMap<>();

    public List<MessageDto> getSessionHistory(String sessionId) {
        return sessionMap.computeIfAbsent(sessionId, k -> new ArrayList<>());
    }

    public void addMessage(String sessionId, MessageDto message) {
        getSessionHistory(sessionId).add(message);
    }
}
