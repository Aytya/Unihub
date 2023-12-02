package com.example.project.web.dto;

import com.example.project.domain.model.Group;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceDto {
    private Long id;
    private Group _group;
    private Long duration;
    private Boolean unlock;
    private Long lastUpdateTime;
    private LocalDateTime startTime;
}
