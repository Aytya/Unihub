package com.example.project.web.mapper;

import com.example.project.domain.model.Attendance;
import com.example.project.web.dto.AttendanceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttendanceMapper extends Mappable<Attendance, AttendanceDto> {
}
