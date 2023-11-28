package com.example.project.service;

import com.example.project.model.domain.User;
import com.example.project.repository.faculty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
