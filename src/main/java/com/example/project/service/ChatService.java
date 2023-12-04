package com.example.project.service;

import com.example.project.domain.exception.ChatMessage;
import com.example.project.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public List<ChatMessage> getAllMessages(){
        return this.chatRepository.findAll();
    }
    public ChatMessage saveChatMessage(ChatMessage chatMessage){
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTimeNow = getFormattedCreationTime(localDateTime);
        chatMessage.setLocalDateTime(dateTimeNow);
        this.chatRepository.save(chatMessage);
        return chatMessage;
    }
    public String getFormattedCreationTime(LocalDateTime creationTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return creationTime.format(formatter);
    }
}
