package com.example.project.web.mapper;

import com.example.project.domain.model.User;
import com.example.project.web.dto.StudentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper extends Mappable<User, StudentDto> {
}
