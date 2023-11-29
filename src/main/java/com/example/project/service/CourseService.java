package com.example.project.service;

import com.example.project.model.exception.ResourceAlreadyExistsException;
import com.example.project.model.exception.ResourceDoesNotExistException;
import com.example.project.model.domain.Course;
import com.example.project.web.dto.CourseCreateDTO;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course saveCourse(CourseCreateDTO course) throws ResourceAlreadyExistsException;

    Course getCourseById(Long id) throws ResourceDoesNotExistException;

    Course updateCourse(Long code, Course course) throws ResourceDoesNotExistException;

    boolean deleteCourseById(Long id) throws ResourceDoesNotExistException;
}