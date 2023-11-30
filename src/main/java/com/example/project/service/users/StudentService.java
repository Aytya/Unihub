package com.example.project.service.users;

import com.example.project.domain.model.UserImage;
import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.domain.model.User;

import java.util.List;

public interface StudentService {

    List<User> getAllStudents();
//    User saveStudent(User student) throws ResourceAlreadyExistsException;
    User getStudentById(Long id) throws ResourceDoesNotExistException;
    User updateStudent(User student, Long id) throws ResourceDoesNotExistException;
    void deleteStudentById(Long id) throws ResourceDoesNotExistException;
    User create(User student);
    void uploadImage(Long id, UserImage image) throws ResourceDoesNotExistException;
}
