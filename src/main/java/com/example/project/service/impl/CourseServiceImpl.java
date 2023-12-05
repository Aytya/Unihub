package com.example.project.service.impl;

import com.example.project.domain.model.Faculty;
import com.example.project.domain.exception.ResourceAlreadyExistsException;
import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.domain.model.Course;
import com.example.project.repository.CourseRepository;
import com.example.project.repository.FacultyRepository;
import com.example.project.web.mapper.CourseMapper2;
import com.example.project.service.users.CourseService;
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
public class CourseServiceImpl implements CourseService {
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
    public Course saveCourse(CourseCreateDTO courseCreateDTO) throws ResourceAlreadyExistsException, ResourceDoesNotExistException {
        Course courseToSave = new Course();
        courseToSave.setCourseName(courseCreateDTO.getCourseName());
        courseToSave.setCourseCode(courseCreateDTO.getCourseCode());
        courseToSave.setCourseCreditHour(courseCreateDTO.getCourseCreditHour());
        courseToSave.setPreRequisite(courseCreateDTO.getPreRequisite());
        courseToSave.setSemester(courseCreateDTO.getSemester());

        Faculty faculty = facultyRepository.findByFacultyName(courseCreateDTO.getFacultyName())
                .orElseThrow(() -> new ResourceDoesNotExistException(courseCreateDTO.getId()));

        courseToSave.setFaculty(faculty);

//        if (courseRepository.findById(courseToSave.getId())) {
//            throw new ResourceAlreadyExistsException(courseToSave.getId());
//        } else
            return courseRepository.save(courseToSave);
    }

    @Override
    public Course getCourseById(Long courseCode) throws ResourceDoesNotExistException {
        Optional<Course> optionalCourse = courseRepository.findById(courseCode);
        if (optionalCourse.isPresent()) {
            return optionalCourse.get();
        } else throw new ResourceDoesNotExistException(courseCode);
    }

    public Optional<Faculty> findFacultyByName(String facultyName) {
        return facultyRepository.findByFacultyName(facultyName);
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
