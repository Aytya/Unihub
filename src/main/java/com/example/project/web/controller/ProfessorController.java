package com.example.project.web.controller;

import com.example.project.model.exception.ResourceAlreadyExistsException;
import com.example.project.model.exception.ResourceDoesNotExistException;
import com.example.project.model.domain.Professor;
import com.example.project.service.users.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/management")
@PreAuthorize("hasRole('ADMIN')")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;
    @GetMapping("/professor/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        try {
            Professor professor = professorService.get(id);
            return ResponseEntity.ok(professor);
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/professor")
    public ResponseEntity<List<Professor>> getProfessor() {
        return ResponseEntity.ok().body(professorService.getAll());
    }
    @GetMapping("/search_professor/{keyword}")
    public List<Professor> search(@PathVariable String keyword){
        List<Professor> professors = professorService.search(keyword);
        if (professors == null)
            throw  new RuntimeException("There is no Professor with searched criteria");
        return professors;
    }
    @PostMapping("/professor/new")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Professor> saveProfessor(@RequestBody Professor professor) {
        try {
            Professor saveProfessor = professorService.save(professor);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveProfessor);
        } catch (ResourceAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
//    @PostMapping("/professor/{id}")
//    @PreAuthorize("hasAuthority('admin:update')")
//    public String updateProfessor(@PathVariable Long id, @RequestBody Professor professor) throws ResourceDoesNotExistException {
//        Professor existingProfessor = professorService.get(id);
//        existingProfessor.setId(id);
//        existingProfessor.setName(professor.getName());
//        existingProfessor.setDepartment(professor.getDepartment());
//        existingProfessor.setQualification(professor.getQualification());
//
//        professorService.update(existingProfessor);
//        return "Professor edited";
//    }
    @DeleteMapping(value = "student/delete/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public void delete(@PathVariable Long id) throws ResourceDoesNotExistException {
        professorService.delete(id);
    }
}
