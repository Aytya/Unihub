package com.example.project.service.journal;

import com.example.project.model.exception.ResourceAlreadyExistsException;
import com.example.project.model.exception.ResourceDoesNotExistException;
import com.example.project.model.domain.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course saveCourse(Course course) throws ResourceAlreadyExistsException;

    Course getCourseById(Long id) throws ResourceDoesNotExistException;

    Course updateCourse(Long code, Course course) throws ResourceDoesNotExistException;

    boolean deleteCourseById(Long id) throws ResourceDoesNotExistException;
}
