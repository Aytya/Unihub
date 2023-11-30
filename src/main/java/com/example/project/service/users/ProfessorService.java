package com.example.project.service.users;

import com.example.project.domain.exception.ResourceAlreadyExistsException;
import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.domain.model.Professor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfessorService {

    Professor save(Professor professor) throws ResourceAlreadyExistsException;
    Professor get(Long id) throws ResourceDoesNotExistException;
    List<Professor> getAll();
    void delete (Long id) throws ResourceDoesNotExistException;
    List<Professor> search(String keyword);
    Professor update(Professor professor);
}
