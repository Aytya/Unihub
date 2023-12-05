package com.example.project.web.controller.faculty;

import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.domain.model.Attendance;
import com.example.project.service.AttendanceService;
import com.example.project.web.mapper.AttendanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/new_attendance")
@RequiredArgsConstructor
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    private final AttendanceMapper attendanceMapper;

    @PostMapping("/create/{group}")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) throws ResourceDoesNotExistException {
        attendance.setLastUpdateTime(System.currentTimeMillis());
        attendance.setStartTime(LocalDateTime.now());
        this.attendanceService.createAttendance(attendance);
        return new ResponseEntity<>(attendance, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<String> deleteAttendance(@RequestParam LocalDateTime startTime) {
        String result = attendanceService.deleteTheAttendance(startTime);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/updateUnlock")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Attendance> updateUnlock(
            @RequestParam LocalDateTime startTime,
            @RequestParam Boolean unlock) {
        int updatedRows = attendanceService.updateUnlock(startTime, unlock);

        if (updatedRows > 0) {
            // If the update was successful, retrieve the updated entity
            Optional<Attendance> updatedAttendance = attendanceService.getTheAttendance(startTime);

            return updatedAttendance.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateUnlockAndDuration")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Attendance> updateUnlockAndDuration(
            @RequestParam LocalDateTime startTime,
            @RequestParam Boolean unlock,
            @RequestParam Long duration
    ) {
        // Update the entity
        int updatedRows = attendanceService.updateUnlockAndDuration(startTime, unlock, duration);

        if (updatedRows > 0) {

            Optional<Attendance> updatedAttendance = attendanceService.getTheAttendance(startTime);

            return updatedAttendance.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendance(@PathVariable Long id){
        try{
            Attendance attendance = this.attendanceService.getAttendanceById(id);
            return new ResponseEntity<>(attendance, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
