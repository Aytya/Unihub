package com.example.project.web.dto;

import com.example.project.model.domain.Faculty;
import com.example.project.model.domain.Professor;
import com.example.project.model.role.Role;
import com.example.project.web.token.Token;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;//username
    private List<String> images;
    private Role role;
    public String department;
    public String program;
    public String yearOfEnrollment;
    public String yearOfSubmission;
    public String dateOfBirth;
    public String idNo;
    public String permanentAddress;
    public String maillingAddress;
    public String phone;
    public String nationality;
//    private Set<Role> roles;
//    private Faculty faculty;
//    private List<Professor> professor;
}
