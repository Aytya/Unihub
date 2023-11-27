package com.example.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FinalProjectApplication {
    private static final Logger logger = LoggerFactory.getLogger(FinalProjectApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

}
