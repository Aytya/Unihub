package com.example.project.service;

import com.example.project.model.attendance.Attendance;
import com.example.project.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public void createAttendance(Attendance attendance){
        this.attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendancesForGroup(String group) {
//        System.out.println(attendanceRepository.findAllByGroup(group));
        return attendanceRepository.findAllByGroup(group);
    }
    public Optional<Attendance> getTheAttendance(LocalDateTime localDateTime){
        Optional<Attendance> attendance = attendanceRepository.findByStartTime(localDateTime);
        return attendance;
    }
    public String deleteTheAttendance(LocalDateTime localDateTime){
        Optional<Attendance> attendance = attendanceRepository.findByStartTime(localDateTime);
        if(attendance.isPresent()){
            System.out.println(attendance);
            this.attendanceRepository.delete(attendance.get());
            return "Deleted successfully";
        }
        return "Can't delete the attendance with this date";
    }

    public int updateUnlock(LocalDateTime localDateTime,
                                          Boolean unlock){
        int updatedRows = attendanceRepository.updateUnlockByStartTime(localDateTime, unlock);

        return updatedRows;
    }

    public int updateUnlockAndDuration(LocalDateTime localDateTime,
                                       Boolean unlock,
                                       Long duration){
        int updatedRows = attendanceRepository.updateUnlockAndDurationByStartTime(
                localDateTime,
                unlock,
                duration);
        return updatedRows;
    }



    @Scheduled(fixedRate = 1000) // Run every 1000 milliseconds (1 second)
    public void decreaseDurationScheduledTask(){
        List<Attendance> allAttendances =  attendanceRepository.findAll();
        for (Attendance attendance : allAttendances) {
            // Decrease the duration based on the elapsed time since the last update
            long elapsedTime = System.currentTimeMillis() - attendance.getLastUpdateTime();
            long decreaseAmount = elapsedTime / 1000; // Assume 1 second = 1 unit of decrease

            if (decreaseAmount > 0) {
                attendance.setDuration(attendance.getDuration() - decreaseAmount);
                attendance.setLastUpdateTime(System.currentTimeMillis());
                attendanceRepository.save(attendance);
            }
            if(attendance.getDuration() < 0){
                attendanceRepository.delete(attendance);
            }


        }
    }
}