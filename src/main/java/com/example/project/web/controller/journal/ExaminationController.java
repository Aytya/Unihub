package com.example.project.web.controller.journal;

import com.example.project.model.journal.ExamList;
import com.example.project.repository.journal.ExamListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/examination/")
public class ExaminationController {

    @Autowired
    ExamListRepository examListRepository;


    @GetMapping("/add")
    public ExamList save(ExamList examList) {
        return examListRepository.save(examList);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        examListRepository.deleteById(id);
    }
}
