package com.example.project.web.dto;

import com.example.project.domain.role.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;//username
    private List<String> images;
    private Role role;
    public String department;//faculty-name
    public String program;
    public String yearOfEnrollment;
    public String yearOfSubmission;
    public String dateOfBirth;
    public String idNo;
    public String permanentAddress;
    public String maillingAddress;
    public String phone;
    public String nationality;
    private List<ProfessorDto> professor;
    private List<CourseCreateDTO> courses;
}
