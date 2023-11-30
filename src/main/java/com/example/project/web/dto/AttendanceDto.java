package com.example.project.web.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceDto {
    private Long id;
    private String _group;
    private Long duration;
    private Boolean unlock;
    private Long lastUpdateTime;
    private LocalDateTime startTime;
}
