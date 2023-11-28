package com.example.project.model.attendance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "attendance")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    private Date startDate;
    private Date endDate;
    private Long duration;
    private Boolean unlock;
    private String ipAddress;
    private Long diffInSeconds;
    private Long diffInMinutes;
    private Long diffInHours;
    private Long diffInDays;
    private Boolean isPresent;
    private Boolean halfDayPresent;
    private Boolean fullDayPresent;


}
