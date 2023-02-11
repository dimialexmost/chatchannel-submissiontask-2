package com.example.chatchannelinluppgift2.config;

import com.example.chatchannelinluppgift2.ws.ChannelStateSocketHandler;
import com.example.chatchannelinluppgift2.ws.ChatRoomStateSocketHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@EnableWebSocket
@Configuration
@AllArgsConstructor
public class SocketConfig implements WebSocketConfigurer {

    private ChannelStateSocketHandler channelStateSocketHandler;
    private ChatRoomStateSocketHandler chatRoomStateSocketHandler;

    private final static String SOCKET_PREFIX = "/sub";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(channelStateSocketHandler, SOCKET_PREFIX + "/channels");


    }
}
