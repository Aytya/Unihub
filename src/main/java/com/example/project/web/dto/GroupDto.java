package com.example.project.web.dto;

import lombok.Data;
import org.apache.kafka.common.message.LeaderAndIsrRequestData;

import java.util.List;

@Data
public class GroupDto {

    private Long id;
    private List<StudentDto> studentDtoList;
}
