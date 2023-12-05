package com.example.project.service;

import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.domain.model.Attendance;
import com.example.project.domain.model.User;
import com.example.project.repository.AttendanceRepository;
import com.example.project.service.users.StudentService;
import com.example.project.web.dto.StudentDto;
import com.example.project.web.mapper.AttendanceMapper;
import com.example.project.web.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private final StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    public void createAttendance(Attendance attendance) throws ResourceDoesNotExistException {
        List<StudentDto> studentList = attendance.getUserList();

        for (StudentDto user : studentList) {
            User fetchedUser = studentService.getStudentById(user.getId());

            if (fetchedUser == null) {
                throw new ResourceDoesNotExistException(user.getId());
            }

            StudentDto userIdAndName = new StudentDto();
            userIdAndName.setId(fetchedUser.getId());
            userIdAndName.setName(fetchedUser.getEmail());

        }

        attendanceRepository.save(attendance);
    }

    public Attendance getAttendanceById(Long id) {
        List<Attendance> attendances = attendanceRepository.findAll();
        for(Attendance attendance: attendances){
            for(StudentDto studentDto: attendance.getUserList()){
                if(studentDto.getId() == id){
                    return attendance;
                }
            }
        }
        return null;
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