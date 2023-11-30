package com.example.project.web.mapper;

import com.example.project.domain.exception.ChatMessage;
import com.example.project.web.dto.ChatDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper extends Mappable<ChatMessage, ChatDto> {
}
