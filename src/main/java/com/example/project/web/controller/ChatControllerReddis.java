package com.example.project.web.controller;

import com.example.project.domain.exception.ChatMessage;
import com.example.project.domain.model.Message;
import com.example.project.repository.chatDAO.ChatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-reddis")
public class ChatControllerReddis {
    @Autowired
    private ChatDao chatDao;

    @PostMapping
    public Message save(@RequestBody Message chatMessage) {
        return chatDao.save(chatMessage);
    }

    @GetMapping
    public List<Message> getAllProducts() {
        return chatDao.findAll();
    }

    @GetMapping("/{id}")
    public Message findProduct(@PathVariable int id) {
        return chatDao.findProductById(id);
    }
    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id)   {
        return chatDao.deleteChatMessage(id);
    }
}
