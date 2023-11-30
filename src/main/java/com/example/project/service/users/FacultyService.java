package com.example.project.service.users;

import com.example.project.domain.model.Faculty;
import com.example.project.domain.exception.ResourceAlreadyExistsException;
import com.example.project.domain.exception.ResourceDoesNotExistException;

import java.util.List;
import java.util.Optional;

public interface FacultyService {

    List<Faculty> getAllFaculty();
    Faculty save(Faculty faculty) throws ResourceAlreadyExistsException;
    Faculty getFacultyById(Long id) throws ResourceDoesNotExistException;
    Faculty update(Faculty faculty, Long id) throws ResourceDoesNotExistException;
    void delete(Long id) throws ResourceDoesNotExistException;
}
