package com.example.project.domain.model;

import com.example.project.web.dto.CourseCreateDTO;
import com.example.project.web.dto.StudentDto;
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

    @ElementCollection
    @CollectionTable(name = "attendance_students", joinColumns = @JoinColumn(name = "attendance_id"))
    private List<StudentDto> userList;

}