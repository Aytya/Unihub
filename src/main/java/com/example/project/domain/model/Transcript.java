package com.example.project.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "transcript")
public class Transcript {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String semester;
    private float semesterCreditHour;
    private float GPA;

    @OneToMany
    private List<Course> course;

    private String studentName;
    private String dean;
    private String courseName;

    private String date;
    private float marks;
    private boolean attendanceStatus;

    private Long totalAttendance;
    private Long totalMarks;
}
