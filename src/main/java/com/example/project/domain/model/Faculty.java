package com.example.project.domain.model;


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
    private Long id;

    private String facultyName;
    private String deanName;
    private String managerName;

    @Column(unique = true,length = 1024)
    private Long phoneNumber;

    @OneToMany(fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name = "faculty", referencedColumnName = "id",nullable = false)
    private List<Course> courseList;

    @OneToMany(fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    @CollectionTable(name = "professors")
    private List<Professor> professorList;

    @OneToMany(fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    @CollectionTable(name = "students")
    private List<User> studentList;
}
