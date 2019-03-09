package com.deltasi.chat.controllers;

import com.deltasi.chat.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    @MessageMapping("/chat.services/message")
    @SendTo("/topic/reply")
    public String processMessageFromClient(@Payload ChatMessage message) throws Exception {
        String name = message.getSender();
        return name;
    }

    @MessageMapping("/chat.services/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }


    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        messagingTemplate.convertAndSend("/errors", exception.getMessage());
        return exception.getMessage();
    }

    @MessageMapping("/chat.services/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }


}
