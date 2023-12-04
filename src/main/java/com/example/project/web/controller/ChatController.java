package com.example.project.web.controller;

import com.example.project.domain.exception.ChatMessage;
import com.example.project.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private KafkaTemplate<String, ChatMessage> kafkaTemplate;
    @GetMapping("/all")
    public List<ChatMessage> allMessage(){
        return this.chatService.getAllMessages();
    }
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) throws InterruptedException {
        this.chatService.saveChatMessage(chatMessage);
        kafkaTemplate.send("topic", chatMessage);
        return chatMessage;
    }
//    @PostMapping("/sendMessage")
////    @SendTo("/topic/public")
//    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) throws InterruptedException {
//        System.out.println(chatMessage);
//        ChatMessage chatMessage1 = this.chatService.saveChatMessage(chatMessage);
//        kafkaTemplate.send("topic", chatMessage1);
//        return chatMessage1;
//    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {

        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
