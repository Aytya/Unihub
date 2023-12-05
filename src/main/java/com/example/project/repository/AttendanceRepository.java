package com.example.project.repository;

import com.example.project.domain.model.Attendance;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

//    @Query("SELECT a._group FROM Attendance a WHERE a.id = :id")
//    Group findGroupById(@Param("id") Long id);
//    @Query("SELECT a FROM Attendance a WHERE a._group = :group")
//    List<Attendance> findAllByGroup(@Param("group") Group group);

    @Query("SELECT a FROM Attendance a WHERE a.startTime = :startTime")
    Optional<Attendance> findByStartTime(@Param("startTime") LocalDateTime startTime);

    @Transactional
    @Modifying
    @Query("UPDATE Attendance a SET a.unlock = :unlock WHERE a.startTime = :startTime")
    int updateUnlockByStartTime(@Param("startTime") LocalDateTime startTime, @Param("unlock") Boolean unlock);

    @Transactional
    @Modifying
    @Query("UPDATE Attendance a SET a.unlock = :unlock, a.duration = :duration WHERE a.startTime = :startTime")
    int updateUnlockAndDurationByStartTime(
            @Param("startTime") LocalDateTime startTime,
            @Param("unlock") Boolean unlock,
            @Param("duration") Long duration
    );
}
