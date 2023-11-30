package com.example.project.web.dto;

import com.example.project.domain.role.MessageType;
import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ChatDto {

    private Long id;
    private String sender;
    private String content;
    private String localDateTime;
    private MessageType Type;
}
