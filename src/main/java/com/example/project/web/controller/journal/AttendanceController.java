package com.example.project.web.controller.journal;

import com.example.project.model.attendance.Attendance;
import com.example.project.service.journal.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:4200")
@RestController
@RequestMapping(path = "/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @PostMapping(path = "/add")
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody Attendance attendance){
        HttpHeaders headers = new HttpHeaders();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(attendanceService.addUser(attendance));
        }catch (Exception e){
            headers.add("Message","false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }
    }

    @GetMapping("/find-all")
    @ResponseBody
    public ResponseEntity<?> findAllUser() {
        HttpHeaders headers = new HttpHeaders();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(attendanceService.findAllUser());
        }catch (Exception e){
            headers.add("Message","false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }
    }

    @GetMapping("/find-emId")
    @ResponseBody
    public ResponseEntity<?> findByempId(@RequestParam String empId){
        HttpHeaders headers = new HttpHeaders();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(attendanceService.findAllUser());
        }catch (Exception e) {
            headers.add("Message", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }
    }

    @GetMapping(path = "/findbyDandmanda")
    @ResponseBody
    public ResponseEntity<?> countByDepartmentIdAndMonthAndAvailable(@RequestParam String employeeid,@RequestParam String departmentid,@RequestParam String month,@RequestParam Boolean available) {
        HttpHeaders headers = new HttpHeaders();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body( attendanceService.countByDepartmentIdAndMonthAndAvailable(departmentid,month,available));
        }catch (Exception e) {
            headers.add("Message", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }
    }

    @GetMapping(path = "/findbyDandm")
    @ResponseBody
    public ResponseEntity<?> countByDepartmentIdAndMonth(@RequestParam String departmentid,@RequestParam String month) {
        HttpHeaders headers = new HttpHeaders();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body( attendanceService.countByDepartmentIdAndMonth(departmentid,month));
        }catch (Exception e) {
            headers.add("Message", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }
    }
    @GetMapping(path = "/findbyEandDandm")
    @ResponseBody
    public ResponseEntity<?> countByEmployeeIdAndDepartmentIdAndMonth(@RequestParam String employeeid,@RequestParam String departmentid,@RequestParam String date) {
        HttpHeaders headers = new HttpHeaders();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body( attendanceService.countByEmployeeIdAndDepartmentIdAndMonth(employeeid,departmentid,date));
        }catch (Exception e) {
            headers.add("Message", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }
    }

    @GetMapping(path = "/findbyEandD")
    @ResponseBody
    public ResponseEntity<?> countByEmployeeIdAndDepartmentId(@RequestParam String employeeid,@RequestParam String departmentid) {
        HttpHeaders headers = new HttpHeaders();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body( attendanceService.countByEmployeeIdAndDepartmentId(employeeid,departmentid));
        }catch (Exception e) {
            headers.add("Message", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }
    }

    @GetMapping(path = "/findbyEandDandA")
    @ResponseBody
    public ResponseEntity<?> countByEmployeeIdAndDepartmentIdAndAvailable(@RequestParam String employeeid,@RequestParam String departmentid,@RequestParam Boolean available) {
        HttpHeaders headers = new HttpHeaders();
        try {
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body( attendanceService.countByEmployeeIdAndDepartmentIdAndAvailable(employeeid,departmentid,available));
        }catch (Exception e) {
            headers.add("Message", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }


    }

    @GetMapping(path = "/findbydepidatten")
    @ResponseBody
    public ResponseEntity<?> findBydeptid(@RequestParam String departmentid) {
        HttpHeaders headers = new HttpHeaders();

        try {
            //System.out.println("en da ipdi "+employeeid+employeeservice.findByempid(employeeid));
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body( attendanceService.findBydeptid(departmentid));
        }catch (Exception e) {
            headers.add("Message", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }


    }

    @GetMapping(path = "/findbydate")
    @ResponseBody
    public ResponseEntity<?> findBydate(@RequestParam String month) {
        HttpHeaders headers = new HttpHeaders();

        try {
            //System.out.println("en da ipdi "+employeeid+employeeservice.findByempid(employeeid));
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(attendanceService.findBydate(month));
        }catch (Exception e) {
            headers.add("Message", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }

    }
    @GetMapping(path = "/sortview")
    @ResponseBody
    public ResponseEntity<?> sortdeptview(@RequestParam String departmentId,@RequestParam String month,@RequestParam String shift) {
        HttpHeaders headers = new HttpHeaders();

        try {
            //System.out.println("en da ipdi "+employeeid+employeeservice.findByempid(employeeid));
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(attendanceService.sortdeptview(departmentId,month,shift));
        }catch (Exception e) {
            headers.add("Message", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }

    }

    @GetMapping(path = "/findbybothidatten")
    @ResponseBody
    public Attendance findByName(@RequestParam String employeeid,@RequestParam String departmentid) {
        return attendanceService.findByName(employeeid, departmentid);
    }


    @GetMapping(path = "/updateatten")
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestParam String employeeid,@RequestParam Boolean available,@RequestParam String date) {
        HttpHeaders headers = new HttpHeaders();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(attendanceService.updateUser(employeeid,available,date));
        }catch (Exception e) {
            headers.add("Message", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }

    }

    @GetMapping(path = "/deletebyempidatten")
    @ResponseBody
    public String deleteByEmpid(@RequestParam String employeeid) {
        return attendanceService.deleteByEmpid(employeeid);
    }


    @GetMapping(path = "/deletebydepidatten")
    @ResponseBody
    public ResponseEntity<?> deleteBydeptid(@RequestParam String departmentid) {
        HttpHeaders headers = new HttpHeaders();

        try {
//            System.out.println("en da ipdi "+employeeid+employeeservice.findByempid(employeeid));
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(attendanceService.deleteBydeptid(departmentid));
        }catch (Exception e) {
            headers.add("Message", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
        }

    }
    @GetMapping(path = "/deletebybothidatten")
    @ResponseBody
    public String deleteByUserNameAndPassword(@RequestParam String employeeid, @RequestParam String departmentid) {
        return attendanceService.deleteByUserNameAndPassword(employeeid, departmentid);
    }


//    @MessageMapping("/attendance.addAttendance")
//    @SendTo("/topic/attendance")
//    public AttendanceMessage addAttendance(
//            @Payload AttendanceMessage attendanceMessage,
//            SimpMessageHeaderAccessor headerAccessor
//    ){
//        headerAccessor.getSessionAttributes().put("firstName", attendanceMessage.getSender());
//        return attendanceMessage;
//    }

//    @MessageMapping("/markAttendance")
//    @SendTo("/topic/attendance")
//    public String markAttendance(String message) {
//
//        return "Attendance Marked: " + message;
//    }
}
