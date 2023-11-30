package com.example.project.web.dto;

import com.example.project.domain.model.Course;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class TranscriptDto {
    private Long id;
    private String semester;
    private float semesterCreditHour;
    private float GPA;
    private List<CourseCreateDTO> course;
    private String studentName;
    private String courseName;
    private String date;
    private float marks;
    private boolean attendanceStatus;
    private Long totalAttendance;
    private Long totalMarks;
}
