package com.example.project.domain.model;

import com.example.project.domain.role.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private LocalDateTime expirationDate;
    private String userId;
//
//    @Column(name = "image")
//    @CollectionTable(name = "tasks_images")
//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<String> images;
}
