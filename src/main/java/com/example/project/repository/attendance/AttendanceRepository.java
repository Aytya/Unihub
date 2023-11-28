package com.example.project.repository.attendance;

import com.example.project.model.attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query("SELECT a FROM attendance a WHERE a._group = :group")
    List<Attendance> findAllByGroup(@Param("group") String group);
}
