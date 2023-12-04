package com.example.project.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Professor {

    @Id
    @Column(name = "professors_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String qualification;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Course> courseList;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<User> studentList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Faculty faculty;
}