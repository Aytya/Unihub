package com.example.project.service;

import com.example.project.domain.model.FinanceCabinet;
import com.example.project.domain.model.User;
import com.example.project.repository.FinanceCabinetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FinanceService {
    @Autowired
    private FinanceCabinetRepository financeRepository;

    public FinanceCabinet createFinanceCabinet(User user, Long semester, String type, Long totalAmount) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (semester == null || semester <= 0) {
            throw new IllegalArgumentException("Invalid semester");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        if (totalAmount == null || totalAmount <= 0) {
            throw new IllegalArgumentException("Invalid total amount");
        }

        FinanceCabinet financeCabinet = new FinanceCabinet();
        financeCabinet.setUser(user);
        financeCabinet.setSemester(semester);
        financeCabinet.setType(type);
        financeCabinet.setTotalAmount(totalAmount);
        financeCabinet.setSubmitAmount(0L); // Assuming no payment has been submitted yet
        financeCabinet.setDate(LocalDateTime.now()); // Set the date to the current date and time

        // Save the new FinanceCabinet to the repository
        return financeRepository.save(financeCabinet);
    }

    public Long calculateRemainingAmount(User user) {
        FinanceCabinet financeCabinet = getFinanceCabinetByUser(user);
        return financeCabinet.getTotalAmount() - financeCabinet.getSubmitAmount();
    }
    public List<FinanceCabinet> getAllFinance(){
        List<FinanceCabinet> financeCabinet = financeRepository.findAll();
        return financeCabinet;
    }
    public boolean isPaymentComplete(User user) {
        FinanceCabinet financeCabinet = getFinanceCabinetByUser(user);
        return financeCabinet.getTotalAmount() <= (financeCabinet.getSubmitAmount());
    }

    public void updatePayment(User user, Long amount) {
        FinanceCabinet financeCabinet = getFinanceCabinetByUser(user);

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        if (isPaymentComplete(user)) {
            throw new IllegalStateException("Payment is already complete");
        }

        financeCabinet.setSubmitAmount(financeCabinet.getSubmitAmount() + amount);
        financeCabinet.setDate(LocalDateTime.now());

        financeRepository.save(financeCabinet);
    }

    public void setDefaultSemester(User user) {
        FinanceCabinet financeCabinet = getFinanceCabinetByUser(user);

        if (financeCabinet.getSemester() == null) {
            financeCabinet.setSemester(1L); // Set a default semester value, adjust as needed
        }

        financeRepository.save(financeCabinet);
    }

    public void validateAndSetType(User user, String newType) {
        FinanceCabinet financeCabinet = getFinanceCabinetByUser(user);

        if (newType == null || newType.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }

        financeCabinet.setType(newType);
        financeRepository.save(financeCabinet);
    }
    public void associateStudent(User user, User student) {
        FinanceCabinet financeCabinet = getFinanceCabinetByUser(user);

        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        financeCabinet.setUser(student);
        financeRepository.save(financeCabinet);
    }

    private FinanceCabinet getFinanceCabinetByUser(User user) {
        return financeRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Finance cabinet not found for user"));
    }
}
