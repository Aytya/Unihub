package com.example.project.web.mapper;


import com.example.project.domain.model.Course;
import com.example.project.web.dto.CourseCreateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper2 {
    Course toCourse(CourseCreateDTO courseCreateDTO);
}