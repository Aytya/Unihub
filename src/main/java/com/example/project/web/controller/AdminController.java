package com.example.project.web.controller;

import com.example.project.web.dto.UserImageDto;
import com.example.project.web.dto.auth.AuthenticationResponse;
import com.example.project.web.dto.auth.StudentRequest;
import com.example.project.web.mapper.UserImageMapper;
import com.example.project.model.domain.UserImage;
import com.example.project.model.exception.ResourceDoesNotExistException;
import com.example.project.model.domain.User;
import com.example.project.web.security.auth.AuthenticationService;
import com.example.project.service.users.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@CrossOrigin("http://127.0.0.1:4200")
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private StudentService studentService;

    private UserImageMapper userImageMapper;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/students/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
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
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) throws ResourceDoesNotExistException {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/student/new")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<AuthenticationResponse> insertStudent(@RequestBody StudentRequest student) {
        return ResponseEntity.ok(authenticationService.registrationStudent(student));
    }

    @PutMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") User student,
                                Model model) throws ResourceDoesNotExistException {

        User existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    @GetMapping(value = "student/all")
    public ResponseEntity<List<User>> getStudent() {
        return ResponseEntity.ok().body(studentService.getAllStudents());
    }
    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public String put() {
        return "PUT:: admin controller";
    }

    @DeleteMapping(value = "student/{id}")
    public void delete(@PathVariable Long id) throws ResourceDoesNotExistException {
        studentService.deleteStudentById(id);
    }
    @GetMapping("/status")
    public String status() {
        return "Working!";
    }

    @PostMapping("student/{id}/image")
    @Operation(summary = "Upload image to task")
    @PreAuthorize("hasAuthority('admin:create')")
    public void uploadImage(@PathVariable Long id, @Validated @ModelAttribute UserImageDto userImage) throws ResourceDoesNotExistException {
            UserImage image = userImageMapper.toEntity(userImage);
            studentService.uploadImage(id,image);
    }

}
