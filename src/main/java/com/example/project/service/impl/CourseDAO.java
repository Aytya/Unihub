package com.example.project.service.impl;

import com.example.project.model.domain.Faculty;
import com.example.project.model.exception.ResourceAlreadyExistsException;
import com.example.project.model.exception.ResourceDoesNotExistException;
import com.example.project.model.domain.Course;
import com.example.project.repository.CourseRepository;
import com.example.project.repository.FacultyRepository;
import com.example.project.web.mapper.CourseMapper2;
import com.example.project.service.CourseService;
import com.example.project.web.dto.CourseCreateDTO;
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
    @Autowired
    private FacultyRepository facultyRepository;
    private final CourseMapper2 courseMapper;

    @Override
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        courseList.addAll((Collection<? extends Course>) courseRepository.findAll());
        return courseList;
    }

    @Override
    public Course saveCourse(CourseCreateDTO courseCreateDTO) throws ResourceAlreadyExistsException {
        Course courseToSave = courseMapper.toCourse(courseCreateDTO);
        Faculty faculty = facultyRepository.findById
                (courseCreateDTO.getFaculty_id()).get();
        courseToSave.setFaculty(faculty);
        Optional<Course> optionalCourse = courseRepository.findById(courseToSave.getCourseCode());
        if (optionalCourse.isPresent()) {
            throw new ResourceAlreadyExistsException(courseToSave.getCourseCode());
        } else {
            Course savedCourse =  courseRepository.save(courseToSave);
            return savedCourse;

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
