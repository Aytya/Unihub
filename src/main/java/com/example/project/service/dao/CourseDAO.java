package com.example.project.service.dao;

import com.example.project.model.exception.ResourceAlreadyExistsException;
import com.example.project.model.exception.ResourceDoesNotExistException;
import com.example.project.model.domain.Course;
import com.example.project.repository.domain.CourseRepository;
import com.example.project.service.journal.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseDAO implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        courseList.addAll((Collection<? extends Course>) courseRepository.findAll());
        return courseList;
    }

    @Override
    public Course saveCourse(Course course) throws ResourceAlreadyExistsException {
        Optional<Course> optionalCourse = courseRepository.findById(course.getCourseCode());
        if (optionalCourse.isPresent()) {
            throw new ResourceAlreadyExistsException(course.getCourseCode());
        } else {
            return courseRepository.save(course);
        }
    }

    @Override
    public Course getCourseById(Long courseCode) throws ResourceDoesNotExistException {
        Optional<Course> optionalCourse = courseRepository.findById(courseCode);
        if (optionalCourse.isPresent()) {
            return optionalCourse.get();
        } else throw new ResourceDoesNotExistException(courseCode);
    }

    @Override
    public Course updateCourse(Long code, Course course) throws ResourceDoesNotExistException {
        Optional<Course> optionalCourse = courseRepository.findById(code);
        if (optionalCourse.isPresent()) {
            course.setCourseCode(code);
            return courseRepository.save(course);
        } else {
            throw new ResourceDoesNotExistException(code);
        }
    }
    @Override
    public boolean deleteCourseById(Long code) throws ResourceDoesNotExistException{
        Optional<Course> optionalCourse = courseRepository.findById(code);
        optionalCourse.ifPresent(course -> courseRepository.deleteById(code));
        optionalCourse.orElseThrow(() -> new ResourceDoesNotExistException(code));
        return true;
    }
}
