package com.example.project.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC_NAME = "topic";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageWithDelay(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }
}