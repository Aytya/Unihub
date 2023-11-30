package com.example.project.domain.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "attendance")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String _group;
    private Long duration;
    private Boolean unlock;
    private Long lastUpdateTime;
    @Nullable
    private LocalDateTime startTime;


}
