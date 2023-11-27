package com.example.project.repository.finance;

import com.example.project.model.finance.RetakeFX;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeeRecordRepository extends JpaRepository<RetakeFX,Integer> {

    @Query("select SUM(totalFee) from RetakeFX f")
    public Integer getGrossIncome();

    @Query("select SUM(totalFee) from RetakeFX f where f.feeSubmittedDate BETWEEN :startDate AND :endDate")
    public Integer getGrossIncomeByDate(@Param("startDate")String startDate,@Param("endDate")String endDate);

    @Query("select SUM (submittedFee) from RetakeFX f")
    public Integer getNetIncome();

    @Query("select s from RetakeFX s")
    public Page<RetakeFX> getAllStudents(Pageable pageable);

}