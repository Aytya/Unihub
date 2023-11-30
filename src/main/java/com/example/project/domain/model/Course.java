package com.example.project.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String courseName;

    @Column(unique =true)
    private Long courseCode;

    private String courseCreditHour;
    private String preRequisite;
    private String semester;
    private String seminarTime;
    private String lectureTime;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "professor", nullable = false)
    private Professor professor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "students_group", nullable = false)
    private List<User> studentList;
}
