package com.example.project.model.finance;

import com.example.project.model.domain.Course;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "fee-record")
public class RetakeFX {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentName;
    private Long semester;

    @OneToOne
    private Course courseName;

    private String type;

    @Column(length = 1000)
    private Long totalFee;

    @Column(length = 1000)
    private Long submittedFee;

    private String feeSubmittedDate;

    private Long creditHour;
}
