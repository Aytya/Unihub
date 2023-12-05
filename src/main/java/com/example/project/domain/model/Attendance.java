package com.example.project.domain.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long duration;
    private Boolean unlock;
    private Long lastUpdateTime;
    @Nullable
    private LocalDateTime startTime;
    @ManyToOne
    @JoinColumn(name = "_group_id")
    private Group _group;
}