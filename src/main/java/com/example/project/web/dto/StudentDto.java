package com.example.project.web.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class StudentDto {

    private Long id;
    private String name;
}
