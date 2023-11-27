package com.example.project.repository.attendance;

import com.example.project.model.attendance.Attendance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {

    Attendance[] findByEmployeeId(String employeeId);


    Attendance[] findByDepartmentId(String departmentId);

    Attendance findBydate(String month);

    Attendance findByEmployeeIdAndDepartmentId(String employeeId,String departmentId);


    Long countByEmployeeIdAndDepartmentId(String employeeId,String departmentId);

    Long countByEmployeeIdAndDepartmentIdAndAvailable(String employeeId,String departmentId,Boolean available);

    Long countByEmployeeIdAndDepartmentIdAndMonth(String employeeId,String departmentId,String month);

    @Query(value = "SELECT count(*) as total FROM attendance where departmentId = ?1 and month = ?2 group by employeeId")
    Long countByDepartmentIdAndMonth(String departmentId,String month);

    Long countByDepartmentIdAndMonthAndAvailable(String departmentId,String month,Boolean available);

    @Transactional
    void deleteByEmployeeIdAndDepartmentId(String employeeId,String departmentId);

    @Transactional
    void deleteByDepartmentId(String departmentId);

    @Transactional
    void deleteByEmployeeId(String employeeId);

    Attendance findByEmployeeIdAndDate(String employeeId, String date);

    @Query(value = "SELECT employeeId,count(available) as total FROM attendance where available=true and departmentId = ?1 and month = ?2 and shift = ?3 group by employeeId order by count(available) desc")
    Object[] findByAttencountOrderByAttencountAsc(String departmentId,String month,String shift);
}
