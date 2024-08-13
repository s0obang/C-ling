package com.example.cling.controller;

import com.example.cling.dto.MyPageResponseDto;
import com.example.cling.dto.ProfileImageDto;
import com.example.cling.dto.ProfileImageResponseDto;
import com.example.cling.service.ProfileImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

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
    public ResponseEntity<ProfileImageDto> getImageAsBase64(@PathVariable String studentId) {
        try {
            // 사용자 ID로 프로필 이미지 URL을 찾기
            ProfileImageResponseDto profileImageResponse = profileImageService.findImage(studentId);

            // 프로필 이미지 URL에서 바이트 배열 가져오기
            byte[] imageBytes = profileImageService.getImageBytes(profileImageResponse.getUrl());

            // 이미지 데이터를 Base64로 인코딩
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            String base64ImageUrl = "data:image/png;base64," + base64Image;  // 이미지 타입에 맞게 수정 가능

            // ProfileImageDto에 Base64 이미지 URL 설정
            ProfileImageDto responseDto = new ProfileImageDto(base64ImageUrl);

            return ResponseEntity.ok(responseDto);

        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}