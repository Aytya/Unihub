package com.example.project.model.domain;


import com.example.project.model.domain.Course;
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

    @OneToMany(mappedBy = "faculty", fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name = "faculty", referencedColumnName = "id",nullable = false)
    private List<Course> courseList;

    @OneToMany(mappedBy = "faculty", fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    @CollectionTable(name = "professors")
    private List<Professor> professorList;

    @OneToMany(mappedBy = "faculty", fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    @CollectionTable(name = "students")
    private List<User> studentList;

}
