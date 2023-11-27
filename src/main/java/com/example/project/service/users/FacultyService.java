package com.example.project.service.users;

import com.example.project.model.domain.Faculty;
import com.example.project.model.exception.ResourceAlreadyExistsException;
import com.example.project.model.exception.ResourceDoesNotExistException;

import java.util.List;

public interface FacultyService {

    List<Faculty> getAllFaculty();
    Faculty save(Faculty faculty) throws ResourceAlreadyExistsException;
    Faculty getFacultyById(Long id) throws ResourceDoesNotExistException;
    Faculty update(Faculty faculty, Long id) throws ResourceDoesNotExistException;
    void delete(Long id) throws ResourceDoesNotExistException;
}
