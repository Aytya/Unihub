package com.example.project.model.domain;

import com.example.project.model.domain.Course;
import com.example.project.model.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "professor")
public class Professor implements Serializable {

    @Id
    @Column(name = "professors_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String qualification;
    private String dean;

    @OneToMany
    private List<Course> courseList;
    @OneToMany
    private List<User> studentList;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
