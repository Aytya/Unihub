package com.example.project.web.mapper;

import com.example.project.domain.model.Transcript;
import com.example.project.web.dto.TranscriptDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TranscriptMapper extends Mappable<Transcript, TranscriptDto> {
}