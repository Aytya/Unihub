package com.example.project.web.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceControllerWS{
    @Autowired
    private AttendanceServiceWS attendanceServiceWS;

    @PostMapping("/start")
    public ResponseEntity<AttendanceStatus> startAttendance() throws InterruptedException {

        if (!attendanceServiceWS.isAttendanceInProgress()) {
            AttendanceStatus attendanceStatus = attendanceServiceWS.startAttendance();
            return new ResponseEntity<>(attendanceStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/end/{id}")
    public ResponseEntity<String> endAttendance(@PathVariable Integer id) {
        if (attendanceServiceWS.isAttendanceInProgress()) {
            attendanceServiceWS.endAttendance(id);
            return new ResponseEntity<>("Attendance ended.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No attendance in progress.", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<AttendanceStatus> getAttendance(@PathVariable Integer id){
        AttendanceStatus attendanceStatus = this.attendanceServiceWS.getAttendance(id);
        if(attendanceStatus != null)
            return new ResponseEntity<>(attendanceStatus, HttpStatus.OK);
        else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @MessageMapping("/markAttendance")
    @SendTo("/topic/attendanceStatus")
    public String markAttendance(String message) {
        // Process attendance marking logic here
        return "Attendance Marked: " + message;
    }
}