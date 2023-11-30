package com.example.project.web.controller;
import com.example.project.domain.model.FinanceCabinet;
import com.example.project.domain.model.User;
import com.example.project.service.FinanceService;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/finance")
public class FinanceController {
    @Autowired
    private FinanceService financeService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public FinanceCabinet createFinanceCabinet(
            @RequestParam String userEmail,
            @RequestParam Long semester,
            @RequestParam String type,
            @RequestParam Long totalAmount
    ) {
        User user = userService.findUserByEmail(userEmail);

        return financeService.createFinanceCabinet(user, semester, type, totalAmount);
    }

    @GetMapping("/remainingAmount")
    public Long calculateRemainingAmount(@RequestParam String userEmail) {

        User user = userService.findUserByEmail(userEmail);


        return financeService.calculateRemainingAmount(user);
    }

    @GetMapping("/paymentComplete")
    public boolean isPaymentComplete(@RequestParam String userEmail) {

        User user = userService.findUserByEmail(userEmail);


        return financeService.isPaymentComplete(user);
    }

    @PutMapping("/updatePayment")
    public void updatePayment(
            @RequestParam String userEmail,
            @RequestParam Long amount
    ) {
        User user = userService.findUserByEmail(userEmail);

        financeService.updatePayment(user, amount);
    }

    @PostMapping("/setDefaultSemester")
    public void setDefaultSemester(@RequestParam String userEmail) {

        User user = userService.findUserByEmail(userEmail);

        financeService.setDefaultSemester(user);
    }

    @PostMapping("/validateAndSetType")
    public void validateAndSetType(
            @RequestParam String userEmail,
            @RequestParam String newType
    ) {

        User user = userService.findUserByEmail(userEmail);


        financeService.validateAndSetType(user, newType);
    }

//    @PostMapping("/associateStudent")
//    public void associateStudent(
//            @RequestParam String userEmail,
//            @RequestParam Long studentId
//    ) {
//
//        User user = userService.findUserByEmail(userEmail);
//        User student = userService.findUserByEmail(userEmail);
//
//        financeService.associateStudent(user, student);
//    }
}
