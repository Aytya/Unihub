package com.example.project.service.impl;

import com.example.project.model.domain.Faculty;
import com.example.project.model.exception.ResourceAlreadyExistsException;
import com.example.project.model.exception.ResourceDoesNotExistException;
import com.example.project.repository.faculty.FacultyRepository;
import com.example.project.service.users.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacultyDAO implements FacultyService {

    @Autowired
    private final FacultyRepository facultyRepository;
    @Override
    public List<Faculty> getAllFaculty() {
        List<Faculty> facultyList = new ArrayList<>();
        facultyList.addAll((Collection<? extends Faculty>) facultyRepository.findAll());
        return facultyList;
    }

    @Override
    public Faculty save(Faculty faculty) throws ResourceAlreadyExistsException {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(faculty.getId());
        if(optionalFaculty.isPresent()) {
            throw new ResourceAlreadyExistsException(faculty.getId());
        } else {
            return facultyRepository.save(faculty);
        }
    }

    @Override
    public Faculty getFacultyById(Long id) throws ResourceDoesNotExistException {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if(optionalFaculty.isPresent()) {
            return optionalFaculty.get();
        } else throw new ResourceDoesNotExistException(id);
    }

    @Override
    public Faculty update(Faculty faculty, Long id) throws ResourceDoesNotExistException {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if(optionalFaculty.isPresent()) {
            faculty.setFacultyName(faculty.getFacultyName());
            faculty.setDeanName(faculty.getDeanName());
            facultyRepository.save(faculty);
        } else {
            throw new ResourceDoesNotExistException(faculty.getId());
        }
        return faculty;
    }

    @Override
    public void delete(Long id) throws ResourceDoesNotExistException {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        optionalFaculty.ifPresent(faculty -> facultyRepository.deleteById(id));
        optionalFaculty.orElseThrow(() -> new ResourceDoesNotExistException(id));
    }

}
