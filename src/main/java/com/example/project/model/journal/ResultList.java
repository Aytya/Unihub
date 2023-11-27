package com.example.project.model.journal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "result-list")
public class ResultList {

    @Id
    @GeneratedValue
    private Long id;
    private String studentName;
    private String semester;
    private String dean;
    private String courseName;

    private String date;
    private float grade;
    private boolean attendanceStatus;

    private Long totalAttendance;
    private Long totalMarks;
}
