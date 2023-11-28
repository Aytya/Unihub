package com.example.project.model.attendance;

import com.example.project.model.domain.User;
import jakarta.annotation.Nullable;
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
//    private String email;

//    private Date startDate;
//    private Date endDate;
    private String _group;
    private Long duration;
    private Boolean unlock;
    private Long lastUpdateTime;
//    private Long diffInSeconds;
//    private Long diffInMinutes;
//    private Long diffInHours;
//    private Long diffInDays;
//    private Boolean isPresent;
//    private Boolean halfDayPresent;
//    private Boolean fullDayPresent;


}
