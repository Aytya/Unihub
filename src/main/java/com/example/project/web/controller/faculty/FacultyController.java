package com.example.project.web.controller.faculty;

import com.example.project.domain.model.Faculty;
import com.example.project.domain.exception.ResourceAlreadyExistsException;
import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.service.users.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping(value = "/new")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Faculty> insertFaculty(@RequestBody Faculty faculty) {
        try {
            Faculty insertedFaculty = facultyService.save(faculty);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedFaculty);
        } catch (ResourceAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        try {
            Faculty faculty = facultyService.getFacultyById(id);
            return ResponseEntity.ok(faculty);
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Faculty>> getAllFaculty() {
        return  ResponseEntity.ok().body(facultyService.getAllFaculty());
    }

    @PutMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty, @PathVariable Long id) {
        try {
            Faculty updateFaculty = facultyService.update(faculty,id);
            return ResponseEntity.ok().body(updateFaculty);
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public void delete(@PathVariable Long id) throws ResourceDoesNotExistException {
        facultyService.delete(id);
    }
}
