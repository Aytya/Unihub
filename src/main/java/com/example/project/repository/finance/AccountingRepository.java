package com.example.project.repository.finance;

import com.example.project.model.finance.FinanceCabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface AccountingRepository extends JpaRepository<FinanceCabinet, Integer> {
    @Query("SELECT SUM(totalAmount) FROM FinanceCabinet where type = 'Registration Fee'")
    public Integer getTotalRegistrationFee();
    @Query("select a from FinanceCabinet a where a.type = :type AND a.semester = :semester AND a.studentId.id = :id")
    public FinanceCabinet getAllStudentsFeeRecordBySemester(@Param(value="type") String type, @Param(value="semester") String semester, @Param(value="id") int id);
    @Query("SELECT SUM(totalAmount) FROM FinanceCabinet where type = 'Registration Fee' AND date BETWEEN :startDate AND :endDate")
    public Integer getTotalRegistrationFeeByDate(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
    @Query("SELECT SUM(totalAmount) FROM FinanceCabinet where type = 'Semester Fee'")
    public Integer getTotalSemesterFee();
    @Query("SELECT SUM(totalAmount) FROM FinanceCabinet where type = 'Semester Fee' AND date BETWEEN :startDate AND :endDate")
    public Integer getTotalSemesterFeeByDate(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
    @Query("update FinanceCabinet a set a.submitAmount = :submitAmount WHERE a.accountId = :id ")
    public int updateFeeRecord(@Param(value="submitAmount") int submitAmount,@Param(value="id") int id);
//    public List<AccountingRecord> getAllStudentsFeeRecord();
    @Query("SELECT a FROM FinanceCabinet a WHERE a.studentId.firstName LIKE :name AND a.semester = :semester OR a.studentId.lastName LIKE :name AND a.semester = :semester")
    public List<FinanceCabinet> findByNameContaining(String name, String semester);


}
