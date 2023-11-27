package com.example.project.repository.journal;

import com.example.project.model.journal.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GPARecordRepository extends JpaRepository<Transcript,Integer> {
//    public List<GPARecord> getAllGPARecordList();
    @Query("select g from Transcript g where g.semester = :semester")
    public Transcript getAllGPARecord(@Param("semester") String semester);

    @Query("update Transcript g set g.GPA = :GPA where g.semester = :semester")
    public int updateGPARecord(float GPA, String semester);

    @Query("update Transcript g set g.semesterCreditHour = :semesterCreditHour  WHERE g.semester = :semester")
    public int updateGPARecordCreditHour(@Param(value="semesterCreditHour") float semesterCreditHour, @Param(value="semester") String semester);
}