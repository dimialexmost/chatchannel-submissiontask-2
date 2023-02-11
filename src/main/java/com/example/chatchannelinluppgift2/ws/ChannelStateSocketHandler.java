package com.example.chatchannelinluppgift2.ws;

import com.example.chatchannelinluppgift2.model.Channel;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChannelStateSocketHandler extends TextWebSocketHandler {
    private List<WebSocketSession> sessions = new ArrayList<>();


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        broadcast(message.getPayload(), session);
    }

    public void broadcast(String message, WebSocketSession webSocketSession) {
        try {
            for (WebSocketSession webSession : sessions) {

                if (!webSocketSession.equals(webSession)) {
                    webSession.sendMessage(new TextMessage(message));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void broadcast(Channel channel) {
        try {
            for (WebSocketSession webSession : sessions) {

                webSession.sendMessage(new TextMessage("Channel " + channel.getTitle() +
                        " was created with id " + channel.getId()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        System.out.println("New session created");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        System.out.println("Session was removed");
    }
}