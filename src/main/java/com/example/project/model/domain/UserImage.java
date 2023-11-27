package com.example.project.model.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserImage {

    private MultipartFile file;
}
