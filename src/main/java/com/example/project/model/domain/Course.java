package com.example.project.model.domain;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;
}
