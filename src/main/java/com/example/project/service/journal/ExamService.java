package com.example.project.service.journal;

import com.example.project.model.exception.ResourceAlreadyExistsException;
import com.example.project.model.exception.ResourceDoesNotExistException;
import com.example.project.model.journal.ExamList;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public interface ExamService{

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    ExamList save(ExamList examList) throws ResourceAlreadyExistsException;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    void deleteById(Long id) throws ResourceDoesNotExistException;

}
