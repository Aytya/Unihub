package com.example.project.service.impl;

import com.example.project.model.domain.UserImage;
import com.example.project.model.exception.ImageUploadException;
import com.example.project.service.users.ImageService;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ImageDAO implements ImageService {

    private final MinioClient minioClient;

    @Override
    public String upload(UserImage userImage) {
        try {
            createBucket();
        }catch (Exception e) {
            throw new ImageUploadException("Image upload failed" + e.getMessage());
        }
        MultipartFile file = userImage.getFile();
        if (file.isEmpty() || file.getOriginalFilename() == null) {
            throw new ImageUploadException("Image upload failed.");
        }
        String fileName = generateFileName(file);
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        }catch (Exception e) {
            throw new ImageUploadException("Image upload failed" + e.getMessage());

        }
        saveImage(inputStream,fileName);
        return fileName;
    }

    @SneakyThrows
    private void createBucket() {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket("asiatrip")
                .build());

//        if (!found) {
//            minioClient.makeBucket(MakeBucketArgs.builder()
//                            .bucket(minioProperties.getBucket())
//                    .build());
//        }
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("images").build());
        } else {
            System.out.println("Bucket 'images' already exists.");
        }
    }

    private String generateFileName(MultipartFile multipartFile) {
        String extension = getExtension(multipartFile);
        return UUID.randomUUID() + "." + extension;
    }

    private String getExtension(MultipartFile multipartFile) {
        return Objects.requireNonNull(multipartFile.getOriginalFilename())
                .substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
    }

    @SneakyThrows
    private void saveImage(InputStream inputStream, String fileName) {
        minioClient.putObject(PutObjectArgs.builder()
                .stream(inputStream,inputStream.available(),-1)
                .bucket("images")
                        .object(fileName)
                .build());
    }
}
