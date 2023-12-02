package com.example.project.web.controller;

import com.example.project.domain.model.Professor;
import com.example.project.web.dto.auth.AuthenticationResponse;
import com.example.project.web.dto.auth.StudentRequest;
import com.example.project.domain.model.UserImage;
import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.domain.model.User;
import com.example.project.web.security.auth.AuthenticationService;
import com.example.project.service.users.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@CrossOrigin("http://127.0.0.1:4200")
@RestController
@RequestMapping("/api/v1/student")
@PreAuthorize("hasRole('ADMIN')")

public class AdminController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<User> getStudentById(@PathVariable Long id) {
        try {
            System.out.println("AdminController");
            User student = studentService.getStudentById(id);
            System.out.println(student);
            System.out.println("AdminController 2");
            return ResponseEntity.ok(student);
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) throws ResourceDoesNotExistException {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<AuthenticationResponse> insertStudent(@RequestBody StudentRequest student) {
        return ResponseEntity.ok(authenticationService.registrationStudent(student));
    }

    @PostMapping("/{id}")
    public ResponseEntity<User> updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") User student) throws ResourceDoesNotExistException {
        return ResponseEntity.ok(studentService.updateStudent(student, id));
    }
    @GetMapping("/{email}/professors")
    public ResponseEntity<List<Professor>> getAllProfessorsOfUser(@PathVariable String email) {
        List<Professor> professors = studentService.findAllProfessorsOfUser(email);

        if (professors != null) {
            return ResponseEntity.ok(professors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getStudent() {
        return ResponseEntity.ok().body(studentService.getAllStudents());
    }
    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public String put() {
        return "PUT:: admin controller";
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) throws ResourceDoesNotExistException {
        studentService.deleteStudentById(id);
    }
    @GetMapping("/status")
    public String status() {
        return "Working!";
    }

    @PostMapping(value = "/{id}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, UserImage userImage) throws ResourceDoesNotExistException {
        studentService.uploadImage(id,userImage);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
