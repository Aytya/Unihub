package com.example.project.web.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendanceServiceWS{
    private boolean isAttendanceInProgress = false;

    @Autowired
    private AttendanceRepositoryWS attendanceRepositoryWS;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public boolean isAttendanceInProgress() {
        return isAttendanceInProgress;
    }

    public AttendanceStatus startAttendance() throws InterruptedException {
        isAttendanceInProgress = true;
        AttendanceStatus attendanceStatus = new AttendanceStatus();
        attendanceStatus.setStatus("Attendance started");
        Thread.sleep(2000);
        broadcastStatusUpdate(attendanceStatus);
        attendanceRepositoryWS.save(attendanceStatus);
        return attendanceStatus;
        // Additional logic if needed
    }

    public void endAttendance(Integer id) {
        isAttendanceInProgress = false;
        Optional<AttendanceStatus> attendanceStatus = attendanceRepositoryWS.findById(id);
        if(attendanceStatus.isPresent()) {
            attendanceStatus.get().setStatus("ended");
            broadcastStatusUpdate(attendanceStatus.get());
            attendanceRepositoryWS.deleteById(id);
        }
        // Additional logic if needed
    }

    public AttendanceStatus getAttendance(Integer id){
        Optional<AttendanceStatus> attendanceStatus = attendanceRepositoryWS.findById(id);
        if(attendanceStatus.isPresent()){
            return attendanceStatus.get();
        }
        else{
            return null;
        }
    }

    private void broadcastStatusUpdate(AttendanceStatus status) {
        messagingTemplate.convertAndSend("/topic/attendanceStatus", status);
    }
}