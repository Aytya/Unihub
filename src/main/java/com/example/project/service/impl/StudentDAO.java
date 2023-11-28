package com.example.project.service.impl;

import com.example.project.model.domain.UserImage;
import com.example.project.model.exception.ResourceDoesNotExistException;
import com.example.project.model.role.Role;
import com.example.project.model.domain.User;
import com.example.project.repository.faculty.UserRepository;
import com.example.project.service.users.ImageService;
import com.example.project.service.users.StudentService;
import com.example.project.web.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentDAO implements StudentService {

    @Autowired
    private UserRepository userRepository;
    private final ImageService imageService;
    private final UserMapper userMapper;
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

    @Override
    @Transactional
    @CacheEvict
    public void uploadImage(Long id, UserImage image) throws ResourceDoesNotExistException {
        User user = getStudentById(id);
        String fileName = imageService.upload(image);
        user.getImages().add(fileName);
        userRepository.save(user);
    }
}
