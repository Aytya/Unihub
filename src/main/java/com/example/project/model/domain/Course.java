package com.example.project.model.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(of = {"code"})
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    private String courseName;
    @Column(unique =true)
    private Long courseCode;
    private String courseCreditHour;
    private String preRequisite;
    private String semester;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
