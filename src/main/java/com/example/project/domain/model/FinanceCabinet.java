package com.example.project.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinanceCabinet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long semester;
    private String type;//retake or fx or just student semester's fee
    private Long totalAmount;//total
    private Long submitAmount;
    private LocalDateTime date;

    @OneToOne
    public User user;
}
