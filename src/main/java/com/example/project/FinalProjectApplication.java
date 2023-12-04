package com.example.project;

import com.example.project.domain.exception.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.example.project.domain.role.MessageType.CHAT;


@SpringBootApplication(scanBasePackages = {"com.example.project","com.example.project.web.mapper"})
@EnableScheduling
@EnableAsync
//@EnableCaching
public class FinalProjectApplication {
    private static final Logger logger = LoggerFactory.getLogger(FinalProjectApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(KafkaTemplate<String, ChatMessage> kafkaTemplate){
//        return args -> {
//            ChatMessage chatMessage = new ChatMessage();
//            chatMessage.setSender("Aza");
//            chatMessage.setContent("Hello");
//            chatMessage.setType(CHAT);
//            kafkaTemplate.send("topic", chatMessage);
//        };
//    }
}
