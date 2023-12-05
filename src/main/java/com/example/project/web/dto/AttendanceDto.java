package com.example.project.web.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AttendanceDto {
    private Long id;
    private Long duration;
    private Boolean unlock;
    private Long lastUpdateTime;
    private LocalDateTime startTime;
    private List<StudentDto> studentDtoList;
}
