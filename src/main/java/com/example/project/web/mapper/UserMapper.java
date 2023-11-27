package com.example.project.web.mapper;


import com.example.project.web.dto.auth.StudentRequest;
import com.example.project.model.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, StudentRequest> {
}