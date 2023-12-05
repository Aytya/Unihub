package com.example.project.repository.chatDAO;

import com.example.project.domain.exception.ChatMessage;
import com.example.project.domain.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatDao {

    public static final String HASH_KEY = "ChatMessage";
    @Autowired
    private RedisTemplate template;

    public Message save(Message chatMessage){
        template.opsForHash().put(HASH_KEY, chatMessage.getId(), chatMessage);
        return chatMessage;
    }

    public List<Message> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Message findProductById(int id){
        return (Message) template.opsForHash().get(HASH_KEY,id);
    }


    public String deleteChatMessage(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "product removed !!";
    }
}
