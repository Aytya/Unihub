package com.example.project.web.mapper;

import com.example.project.domain.model.Task;
import com.example.project.web.dto.TaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDto>{
}
