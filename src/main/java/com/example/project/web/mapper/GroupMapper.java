package com.example.project.web.mapper;

import com.example.project.domain.model.Group;
import com.example.project.web.dto.GroupDto;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface GroupMapper extends Mappable<Group, GroupDto> {
}
