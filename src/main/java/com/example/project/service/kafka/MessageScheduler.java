package com.example.project.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageScheduler {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Scheduled(fixedDelay = 5000)
    public void sendDelayedMessage() {
        String message = "Your delayed message content";
        kafkaProducerService.sendMessageWithDelay(message);
    }
}
