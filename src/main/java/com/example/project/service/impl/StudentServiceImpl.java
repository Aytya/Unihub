package com.example.project.service.impl;

import com.example.project.domain.model.Professor;
import com.example.project.domain.model.UserImage;
import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.domain.role.Role;
import com.example.project.domain.model.User;
import com.example.project.repository.UserRepository;
import com.example.project.service.users.ImageService;
import com.example.project.service.users.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserRepository userRepository;

    private final ImageService imageService;
    @Override
    public List<User> getAllStudents() {
        List<User> studentList = new ArrayList<>();
        studentList.addAll((Collection<? extends User>) userRepository.findAll());
        return studentList;
    }

//    @Override
//    public User saveStudent(User student) throws ResourceAlreadyExistsException {
//
//        Optional<User> optionalStudent = userRepository.findById(student.getId());
//        if(optionalStudent.isPresent()) {
//            throw new ResourceAlreadyExistsException(student.getId());
//        } else {
//            return userRepository.save(student);
//        }
//    }

    @Override
    public User getStudentById(Long id) throws ResourceDoesNotExistException {
        Optional<User> optionalStudent = userRepository.findById(id);
        System.out.println("StudentDAO");
        if (optionalStudent.isPresent()) {
            System.out.println("StudentDAO isPresent");
            return optionalStudent.get();
        }else throw new ResourceDoesNotExistException(id);
    }

    @Override
    public List<Professor> findAllProfessorsOfUser(String userEmail) {
        User user = userRepository.findUserByEmail(userEmail);

        if (user != null && user.getProfessor() != null) {
            return user.getProfessor();
        }

        return null;
    }

    @Transactional
    @Override
    public User updateStudent(User user, Long id) throws ResourceDoesNotExistException {
        User existingStudent = getStudentById(id);
//        userMapper.updateStudentFromDto(userDto, existingStudent);
//        existingStudent.setEmail(user.getEmail());
//        existingStudent.setFaculty(user.getFaculty());
//        existingStudent.setDepartment(user.getDepartment());
//        existingStudent.setPhone(user.getPhone());
        userRepository.save(existingStudent);
        return existingStudent;
    }

    @Override
    public void deleteStudentById(Long id) throws ResourceDoesNotExistException{
        Optional<User> optionalStudent = userRepository.findById(id);
        optionalStudent.ifPresent(student -> userRepository.deleteById(id));
        optionalStudent.orElseThrow(() -> new ResourceDoesNotExistException(id));
    }


    @Override
    public User create(final User student) {
        if (userRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new IllegalStateException("User already exists. ");
        }
        if (!student.getPassword().equals(student.getPasswordConfirmation())) {
            throw new IllegalStateException(
                    "Password and password confirmation do not match."
            );
        }
        Set<Role> roles = Set.of(Role.USER);
        student.setRoles(roles);
        userRepository.save(student);
        return student;
    }

    @PostMapping("/{id}/image")
    public void uploadImage(final Long id, final UserImage image) {
        String fileName = imageService.upload(image);
        userRepository.addImage(id, fileName);
    }

}
