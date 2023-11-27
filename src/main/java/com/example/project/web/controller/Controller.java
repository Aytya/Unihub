package com.example.project.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/controller")
public class Controller {
    @GetMapping
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("Submit!");
    }
}
