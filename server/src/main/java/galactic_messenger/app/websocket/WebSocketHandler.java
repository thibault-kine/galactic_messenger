package main.java.galactic_messenger.app.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {
    
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        
    }
}
