package com.example.project.service.journal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.project.model.attendance.Attendance;
//import com.example.project.repository.attendance.AttendanceRepository;

@Service("attendanceservice")
@RequiredArgsConstructor
public class AttendanceService {
//    @Autowired
//    private AttendanceService attendanceService;

//    public String addUser(Attendance attendance) {
//
//        attendancerepository.save(attendance);
//        return "User account has been added, Employee ID = " + attendance.getEmployeeId();
//
//
//    }
//
//    public List<Attendance> findAllUser() {
//        List<Attendance> attendanceList = (List<Attendance>) attendancerepository.findAll();
//
//        if (attendanceList != null) {
//            return attendanceList;
//        }
//        return null;
//    }
//
//    public Attendance[] findByempid(String employeeid) {
//        Attendance[] attendanceList =  attendancerepository.findByEmployeeId(employeeid);
//
//        if (attendanceList != null) {
//            return attendanceList;
//        }
//        return null;
//    }
//
//
//    public long countByEmployeeIdAndDepartmentId(String employeeid,String departmentid) {
//        long attendanceList =  attendancerepository.countByEmployeeIdAndDepartmentId(employeeid,departmentid);
//        return attendanceList;
//
//    }
//
//    public long countByDepartmentIdAndMonthAndAvailable(String departmentid,String month,Boolean available) {
//        long attendanceList =  attendancerepository.countByDepartmentIdAndMonthAndAvailable(departmentid,month,available);
//        return attendanceList;
//
//    }
//    public long countByDepartmentIdAndMonth(String departmentid,String month) {
//        long attendanceList =  attendancerepository.countByDepartmentIdAndMonth(departmentid,month);
//        return attendanceList;
//
//    }
//    public long countByEmployeeIdAndDepartmentIdAndMonth(String employeeid,String departmentid,String month) {
//        long attendanceList =  attendancerepository.countByEmployeeIdAndDepartmentIdAndMonth(employeeid,departmentid,month);
//        return attendanceList;
//
//    }
//
//    public long countByEmployeeIdAndDepartmentIdAndAvailable(String employeeid,String departmentid,Boolean available) {
//        long attendanceList =  attendancerepository.countByEmployeeIdAndDepartmentIdAndAvailable(employeeid,departmentid,available);
//        return attendanceList;
//
//    }
//
//    public Attendance findBydate(String month) {
//        Attendance attendanceList = (Attendance) attendancerepository.findBydate(month);
//
//        if (attendanceList != null) {
//            return attendanceList;
//        }
//        return null;
//    }
//
//    public Attendance[] findBydeptid(String departmentid) {
//        Attendance[] attendanceList = (Attendance[]) attendancerepository.findByDepartmentId(departmentid);
//        if (attendanceList != null) {
//            return attendanceList;
//        }
//        return null;
//    }
//
//    public Attendance findByName(String employeeid,String departmentid) {
//        Attendance attendanceList = (Attendance) attendancerepository.findByEmployeeIdAndDepartmentId(employeeid, departmentid);
//
//        if (attendanceList != null) {
//            return attendanceList;
//        }
//
//        return null;
//    }
//
//
//    public Object[] sortdeptview(String departmentid,String month,String shift) {
////						AttendanceDetail[] attendanceList = (AttendanceDetail[])
////								attendancerepository.findByDepartmentId(departmentid);
////
////			for(AttendanceDetail atten : attendanceList) {
////				atten.setAttencount(countByDepartmentIdAndMonth(departmentid,month));
////			}
//        return attendancerepository.findByAttencountOrderByAttencountAsc(departmentid,month,shift);
//
//    }
//
//
//
//
//    public String updateUser(String employeeid,Boolean available,String date) {
//        Attendance attendanceList = (Attendance) attendancerepository.findByEmployeeIdAndDate(employeeid,date);
//        if (attendanceList != null) {
//
//            attendanceList.setAvailable(available);
//            attendancerepository.save(attendanceList);
//            return "User updated Successfully";
//        }
//        return "User update Failed";
//    }
//
//
//    public String deleteByEmpid(String employeeid) {
//        attendancerepository.deleteByEmployeeId(employeeid);
//        return "Record deleted successfully";
//    }
//
//    public String deleteBydeptid(String departmentid) {
//        attendancerepository.deleteByDepartmentId(departmentid);
//        return "Record deleted successfully";
//    }
//
//
//    public String deleteByUserNameAndPassword(String employeeid,String departmentid) {
//        attendancerepository.deleteByEmployeeIdAndDepartmentId(employeeid,departmentid);
//        return "Record deleted successfully";
//    }
}