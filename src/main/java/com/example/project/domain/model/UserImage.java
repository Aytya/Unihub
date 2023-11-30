package com.example.project.domain.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class UserImage {

    private MultipartFile file;
}
