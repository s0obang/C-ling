//ProfileImageController
package com.example.cling.controller;

import com.example.cling.dto.MyPageResponseDto;
import com.example.cling.dto.ProfileImageByteDto;
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
    public ResponseEntity<ProfileImageByteDto> getImageAsBase64(@PathVariable String studentId) throws IOException {
        // 사용자 ID로 프로필 이미지 URL을 찾기
        ProfileImageResponseDto profileImageResponse = profileImageService.findImage(studentId);

        // 프로필 이미지 URL에서 바이트 배열 가져오기
        byte[] imageBytes = profileImageService.getImageBytes(profileImageResponse.getUrl());

        ProfileImageByteDto profileImageByteDto = new ProfileImageByteDto(imageBytes);

        return ResponseEntity.ok(profileImageByteDto);


    }

}