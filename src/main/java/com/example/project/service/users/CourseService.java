package com.example.project.service.users;

import com.example.project.domain.exception.ResourceAlreadyExistsException;
import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.domain.model.Course;
import com.example.project.web.dto.CourseCreateDTO;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course saveCourse(CourseCreateDTO course) throws ResourceAlreadyExistsException, ResourceDoesNotExistException;

    Course getCourseById(Long id) throws ResourceDoesNotExistException;

    Course updateCourse(Long code, Course course) throws ResourceDoesNotExistException;

    boolean deleteCourseById(Long id) throws ResourceDoesNotExistException;
}