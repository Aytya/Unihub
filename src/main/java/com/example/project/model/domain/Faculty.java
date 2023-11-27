package com.example.project.model.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "faculty")
public class Faculty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long faculty_id;
    private String facultyName;
    private String deanName;
    private String managerName;

    @Column(unique = true,length = 1024)
    private Long phoneNumber;

    @OneToMany
    @CollectionTable(name = "courses")
    private List<Course> courseList;

    @OneToMany
    @CollectionTable(name = "professors")
    private List<Professor> professorList;

    @OneToMany
    @CollectionTable(name = "students")
    private List<User> studentList;
}
