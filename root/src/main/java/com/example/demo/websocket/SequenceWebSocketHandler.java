package com.example.demo.websocket;

import com.example.demo.model.SequenceData;
import com.example.demo.service.SequenceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class SequenceWebSocketHandler extends TextWebSocketHandler {
    
    private final SequenceService sequenceService;
    private final ObjectMapper objectMapper;
    private final ConcurrentHashMap<String, WebSocketSession> sessions;
    private final ScheduledExecutorService scheduler;
    
    public SequenceWebSocketHandler(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
        this.sessions = new ConcurrentHashMap<>();
        this.scheduler = Executors.newScheduledThreadPool(1);
        
        startStreaming();
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
        System.out.println("WebSocket connection established: " + session.getId());
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
        System.out.println("WebSocket connection closed: " + session.getId());
    }
    
    private void startStreaming() {
        scheduler.scheduleAtFixedRate(() -> {
            if (!sessions.isEmpty()) {
                try {
                    SequenceData sequenceData = sequenceService.generateNext();
                    
                    String jsonMessage = objectMapper.writeValueAsString(sequenceData);
                    TextMessage message = new TextMessage(jsonMessage);
                    
                    sessions.values().forEach(session -> {
                        if (session.isOpen()) {
                            try {
                                session.sendMessage(message);
                            } catch (IOException e) {
                                System.err.println("Error sending message to session " + session.getId() + ": " + e.getMessage());
                            }
                        }
                    });
                    
                } catch (Exception e) {
                    System.err.println("Error generating or sending sequence data: " + e.getMessage());
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.err.println("Transport error on session " + session.getId() + ": " + exception.getMessage());
    }
}
