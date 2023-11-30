package com.example.project.web.mapper;

import com.example.project.web.dto.UserImageDto;
import com.example.project.domain.model.UserImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserImageMapper extends Mappable<UserImage, UserImageDto> {

}