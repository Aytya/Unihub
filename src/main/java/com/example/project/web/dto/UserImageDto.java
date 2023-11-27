package com.example.project.web.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class UserImageDto {
    @NotNull(message = "Image must be not null.")
    private MultipartFile file;
}
