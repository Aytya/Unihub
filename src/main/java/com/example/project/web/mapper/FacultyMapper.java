package com.example.project.web.mapper;

import com.example.project.domain.model.Faculty;
import com.example.project.web.dto.FacultyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacultyMapper extends Mappable<Faculty, FacultyDto >{
}
