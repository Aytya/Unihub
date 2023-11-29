package com.example.project.web.controller.faculty;

import com.example.project.model.attendance.Attendance;
import com.example.project.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/new_attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance){
        attendance.setLastUpdateTime(System.currentTimeMillis());
        this.attendanceService.createAttendance(attendance);
        return new ResponseEntity<>(attendance, HttpStatus.CREATED);
    }

    @GetMapping("/{group}")
    public ResponseEntity<List<Attendance>> getAttendance(@PathVariable String group){
        try{
            List<Attendance> attendanceList = this.attendanceService.getAllAttendancesForGroup(group);
            return new ResponseEntity<>(attendanceList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }


    }
}
