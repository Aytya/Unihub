package com.example.project.model.attendance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

@Entity(name = "attendance")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeId;

    private String month;

    private String date;

    private String departmentId;

    private Boolean available;

    private String checkin;

    private String checkout;

    private long attencount;

    String shift;

}
