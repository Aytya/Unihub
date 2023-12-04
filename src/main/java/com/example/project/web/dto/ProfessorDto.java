package com.example.project.web.dto;

import com.example.project.domain.model.Course;
import com.example.project.domain.model.Faculty;
import com.example.project.domain.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class ProfessorDto {

    private Long id;
    private String name;
    private String qualification;
    private String facultyName;
}
