package com.example.cling.controller;

import com.example.cling.service.ProfileImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile-image")
public class ProfileImageController {

    private final ProfileImageService profileImageService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
                                         @RequestParam("studentId") String studentId) {
        try {
            profileImageService.upload(file, studentId);
            return ResponseEntity.ok("{\"message\": \"이미지 업로드 성공\", \"status\": 200}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"이미지 업로드 실패\", \"status\": 400}");
        }
    }
}