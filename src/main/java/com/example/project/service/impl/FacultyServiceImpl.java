package com.example.project.service.impl;

import com.example.project.domain.model.Faculty;
import com.example.project.domain.exception.ResourceAlreadyExistsException;
import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.repository.FacultyRepository;
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
public class FacultyServiceImpl implements FacultyService {

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
    public Faculty update(final Faculty faculty, Long id) throws ResourceDoesNotExistException {
        Faculty optionalFaculty = getFacultyById(id);
        optionalFaculty.setFacultyName(faculty.getFacultyName());
        optionalFaculty.setDeanName(faculty.getDeanName());
        optionalFaculty.setManagerName(faculty.getManagerName());
        optionalFaculty.setPhoneNumber(faculty.getPhoneNumber());

        facultyRepository.save(faculty);
        return faculty;
    }

    @Override
    public void delete(Long id) throws ResourceDoesNotExistException {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        optionalFaculty.ifPresent(faculty -> facultyRepository.deleteById(id));
        optionalFaculty.orElseThrow(() -> new ResourceDoesNotExistException(id));
    }

}
