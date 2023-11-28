package com.example.project.web.mapper;


import com.example.project.web.dto.UserDto;
import com.example.project.model.domain.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateStudentFromDto(UserDto dto, @MappingTarget User entity);

}