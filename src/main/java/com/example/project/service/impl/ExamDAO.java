package com.example.project.service.impl;

import com.example.project.model.exception.ResourceAlreadyExistsException;
import com.example.project.model.exception.ResourceDoesNotExistException;
import com.example.project.model.journal.ExamList;
import com.example.project.repository.journal.ExamListRepository;
import com.example.project.service.journal.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamDAO implements ExamService {

    @Autowired
    private ExamListRepository exam;

    public ExamList save(ExamList examList) throws ResourceAlreadyExistsException {
        Optional<ExamList> examListOptional = exam.findById(examList.getId());
        if(examListOptional.isPresent()) {
            throw new ResourceAlreadyExistsException(examList.getId());
        } else {
            return exam.save(examList);
        }
    }

    @Override
    public void deleteById(Long id) throws ResourceDoesNotExistException {
        Optional<ExamList> optionalExamList = exam.findById(id);
        optionalExamList.ifPresent(examList -> exam.deleteById(id));
        optionalExamList.orElseThrow(() -> new ResourceDoesNotExistException(id));
    }
}
