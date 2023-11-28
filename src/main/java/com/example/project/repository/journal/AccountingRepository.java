package com.example.project.repository.journal;

import com.example.project.model.journal.FinanceCabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface AccountingRepository extends JpaRepository<FinanceCabinet, Long> {
    @Query("SELECT SUM(totalAmount) FROM FinanceCabinet where type = 'Registration Fee'")
    Long getTotalRegistrationFee();
    @Query("select a from FinanceCabinet a where a.type = :type AND a.semester = :semester AND a.studentId.id = :id")
    FinanceCabinet getAllStudentsFeeRecordBySemester(@Param(value="type") String type, @Param(value="semester") String semester, @Param(value="id") Long id);
    @Query("SELECT SUM(totalAmount) FROM FinanceCabinet where type = 'Registration Fee' AND date BETWEEN :startDate AND :endDate")
    Long getTotalRegistrationFeeByDate(@Param("startDate") String startDate,@Param("endDate") String endDate);
    @Query("SELECT SUM(totalAmount) FROM FinanceCabinet where type = 'Semester Fee'")
    Long getTotalSemesterFee();
    @Query("SELECT SUM(totalAmount) FROM FinanceCabinet where type = 'Semester Fee' AND date BETWEEN :startDate AND :endDate")
    Long getTotalSemesterFeeByDate(@Param("startDate") String startDate,@Param("endDate") String endDate);
//    @Query("update FinanceCabinet a set a.submitAmount = :submitAmount WHERE a.accountId = :id ")
//    public Long updateFeeRecord(@Param(value="submitAmount") int submitAmount,@Param(value="id") int id);
//    @Query("SELECT a FROM FinanceCabinet a WHERE a.studentId.firstName LIKE :name AND a.semester = :semester OR a.studentId.lastName LIKE :name AND a.semester = :semester")
//    public List<FinanceCabinet> findByNameContaining(String name, String semester);
}
