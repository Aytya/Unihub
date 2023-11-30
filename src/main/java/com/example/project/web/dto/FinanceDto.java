package com.example.project.web.dto;

import com.example.project.domain.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.sql.Date;

@Data
public class FinanceDto {
    private Long id;
    private Long semester;
    private String type;//retake or fx or just student semester's fee
    private Long totalAmount;//total
}
