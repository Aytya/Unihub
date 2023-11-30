package com.example.project.web.mapper;

import com.example.project.domain.model.Professor;
import com.example.project.web.dto.ProfessorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessorMapper extends Mappable<Professor, ProfessorDto> {
}
