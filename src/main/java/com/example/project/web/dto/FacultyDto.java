package com.example.project.web.dto;

import com.example.project.domain.model.Course;
import com.example.project.domain.model.Professor;
import com.example.project.domain.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class FacultyDto {

    private Long id;
    private String facultyName;
    private String deanName;
    private String managerName;
    private Long phoneNumber;
    private List<CourseCreateDTO> courseList;
    private List<Professor> professorList;
    private List<UserDto> studentList;
}
