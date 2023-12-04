package com.example.project.service.kafka;

import com.example.project.domain.exception.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerListener {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @KafkaListener(topics = "topic")
    @EventListener
    public void listen(ChatMessage chatMessage) {
        System.out.println(chatMessage);
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}