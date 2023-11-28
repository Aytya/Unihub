package com.example.project.web.controller.journal;

import com.example.project.model.domain.User;
import com.example.project.model.domain.UserImage;
import com.example.project.model.exception.ResourceDoesNotExistException;
import com.example.project.model.journal.FinanceCabinet;
import com.example.project.repository.journal.AccountingRepository;
import com.example.project.web.dto.UserImageDto;
import com.example.project.web.dto.auth.AuthenticationResponse;
import com.example.project.web.dto.auth.StudentRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounting")
public class AccountingController {

    @Autowired
    private AccountingRepository accountingRepository;

    @GetMapping("/registration-fee")
    @PreAuthorize("hasRole('ADMIN')")
    public Long countRegistrationFee(){
        return accountingRepository.getTotalRegistrationFee();
    }
    @GetMapping("/fee-record/{type}/{semester}/{id}")
    public FinanceCabinet getAllStudentsFeeRecordBySemester(@PathVariable String type,@PathVariable String semester,@PathVariable Long id) {
        return accountingRepository.getAllStudentsFeeRecordBySemester(type,semester, id);
    }
    @GetMapping("/gross-income/{startDate}/{endDate}")
    public Long getTotalRegistrationFeeByDate(@PathVariable String startDate,@PathVariable String endDate) {
        return accountingRepository.getTotalRegistrationFeeByDate(startDate, endDate);
    }
    @GetMapping("/semester-fee")
    public Long getTotalSemesterFee(){
        return accountingRepository.getTotalSemesterFee();
    }
    @GetMapping("/semester-fee/{startDate}/{endDate}")
    public Long getTotalSemesterFeeByDate(@PathVariable String startDate,@PathVariable String endDate) {
        return accountingRepository.getTotalSemesterFeeByDate(startDate, endDate);
    }

    @GetMapping("/finance-cabinet/{id}")
    public ResponseEntity<FinanceCabinet> get(@PathVariable Long id) {
        try {
            FinanceCabinet financeCabinet = accountingRepository.getById(id);
            return ResponseEntity.ok(financeCabinet);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/finance-cabinet/new")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<FinanceCabinet> insert(@RequestBody FinanceCabinet financeCabinet) {
        return ResponseEntity.ok(accountingRepository.save(financeCabinet));
    }

}
