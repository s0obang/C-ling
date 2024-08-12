package com.example.cling.controller;

import com.example.cling.dto.ProfileImageResponseDto;
import com.example.cling.service.ProfileImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @GetMapping("/get/{studentId}")
    public ResponseEntity<byte[]> getImage(@PathVariable String studentId) {
        try {
            ProfileImageResponseDto profileImageResponse = profileImageService.findImage(studentId);
            byte[] imageBytes = profileImageService.getImageBytes(profileImageResponse.getUrl());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg"); // 예시로 JPEG 타입을 사용합니다. 실제 이미지 타입에 맞게 조정 필요.
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + studentId + ".jpg\"");

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
