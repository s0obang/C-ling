package com.example.cling.controller;

import com.example.cling.entity.ProfileImage;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.ProfileImageRepository;
import com.example.cling.repository.UserRepository;
import com.example.cling.service.ProfileImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/profile-image")
@RequiredArgsConstructor
public class ProfileImageController {

    private final UserRepository userRepository;
    private final ProfileImageRepository profileImageRepository;
    private final ProfileImageService profileImageService;

    @Value("${file.path}")
    private String uploadFolder;

    @GetMapping("/get/{studentId}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable String studentId) {
        try {
            UserEntity user = userRepository.findByStudentId(studentId)
                    .orElseThrow(() -> new RuntimeException("User not found with studentId: " + studentId));

            ProfileImage profileImage = profileImageRepository.findByUser(user)
                    .orElseThrow(() -> new RuntimeException("Profile image not found for user"));

            Path filePath = Paths.get(uploadFolder + profileImage.getUrl());
            byte[] imageBytes = Files.readAllBytes(filePath);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // 이미지 형식에 맞게 설정
                    .body(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException("Image retrieval failed", e);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProfileImage(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("studentId") String studentId) {
        try {
            profileImageService.upload(file, studentId);
            return ResponseEntity.ok("{\"message\": \"이미지 업로드 성공\", \"status\": 200}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"이미지 업로드 실패\", \"status\": 400}");
        }
    }
}
