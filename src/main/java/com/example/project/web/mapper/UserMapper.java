package com.example.project.web.mapper;


import com.example.project.web.dto.UserDto;
import com.example.project.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateStudentFromDto(UserDto dto, @MappingTarget User entity);

}