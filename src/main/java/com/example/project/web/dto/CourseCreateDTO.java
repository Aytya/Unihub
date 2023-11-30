package com.example.project.web.dto;

import lombok.Data;

@Data
public class CourseCreateDTO {

    private Long id;

    private String courseName;
    private Long courseCode;
    private String courseCreditHour;
    private String preRequisite;
    private String semester;

    private String facultyName;
    private String professorName;

    private String seminarTime;
    private String lectureTime;
}
