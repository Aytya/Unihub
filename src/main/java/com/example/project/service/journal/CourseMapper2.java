package com.example.project.service.journal;


import com.example.project.model.domain.Course;
import com.example.project.web.dto.CourseCreateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper2 {
    Course toCourse(CourseCreateDTO courseCreateDTO);
}