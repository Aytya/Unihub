package com.example.project.web.dto;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CourseCreateDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nullable
    private String courseName;
    @Nullable
    private Long courseCode;
    @Nullable
    private String courseCreditHour;
    @Nullable
    private String preRequisite;
    @Nullable
    private String semester;
    @Nullable
    private String facultyName;
    @Nullable
    private String professorName;
    @Nullable
    private String seminarTime;
    @Nullable
    private String lectureTime;
}
