package com.example.project.model.journal;

import com.example.project.model.domain.User;
import com.example.project.model.domain.Course;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "exam-list")
public class ExamList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean feeSubmitted;
    private String studentName;
    private String semester;
    private String dean;
    private String examType;//midterm and e.t.c
    private Long examResult;
    private String submittedDate;

    @OneToMany
    private List<User> studentList;

    @OneToOne
    private Course courseName;
}
