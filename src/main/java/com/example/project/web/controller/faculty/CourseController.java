package com.example.project.web.controller.faculty;

import com.example.project.domain.exception.ResourceAlreadyExistsException;
import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.domain.model.Course;
import com.example.project.service.CourseService;
import com.example.project.web.dto.CourseCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("http://127.0.0.1:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;



    @PostMapping(value = "/new")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Course> insertCourse(@RequestBody CourseCreateDTO courseCreateDTO) throws ResourceAlreadyExistsException {
        try {
            Course insertedCourse = courseService.saveCourse(courseCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedCourse);
        } catch (ResourceAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = "/{code}")
    public ResponseEntity<Course> getCourse(@PathVariable Long code) throws ResourceDoesNotExistException {
        try {
            Course course = courseService.getCourseById(code);
            return ResponseEntity.ok(course);
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Course>> getCourse() {
        return ResponseEntity.ok().body(courseService.getAllCourses());
    }

    @PutMapping(value = "/update/{code}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Course> updateCourse(@RequestBody @PathVariable Long code, @RequestBody Course course) {
        try {
            Course updateCourse = courseService.updateCourse(code, course);
            return ResponseEntity.ok().body(updateCourse);
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{code}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable Long code) {
        try {
            boolean deleted = courseService.deleteCourseById(code);
            return ResponseEntity.ok(deleted);
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
