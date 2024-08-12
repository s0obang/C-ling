package com.example.cling.controller;

import com.example.cling.dto.MyPageResponseDto;
import com.example.cling.dto.ProfileImageResponseDto;
import com.example.cling.service.UserService;
import com.example.cling.service.ProfileImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/my-page")
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;
    private final ProfileImageService profileImageService;

    @GetMapping("/user-info")
    public ResponseEntity<MyPageResponseDto> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        String studentId = userDetails.getUsername();  // JWT 토큰에서 사용자 정보를 가져오는 방식
        MyPageResponseDto userInfo = userService.getUserInfo(studentId);
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/position-and-crew")
    public ResponseEntity<MyPageResponseDto> getPositionAndCrew(@AuthenticationPrincipal UserDetails userDetails) {
        String studentId = userDetails.getUsername();  // JWT 토큰에서 사용자 정보를 가져오는 방식
        MyPageResponseDto positionAndCrew = userService.getUserPositionAndCrew(studentId);

        // 프로필 이미지 URL을 바이너리 데이터로 변환
        try {
            ProfileImageResponseDto profileImageResponse = profileImageService.findImage(studentId);
            byte[] imageBytes = profileImageService.getImageBytes(profileImageResponse.getUrl());

            // 이미지 데이터를 Base64로 인코딩
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            positionAndCrew.setProfileImageUrl("data:image/png;base64," + base64Image);  // 이미지 타입에 맞게 수정 가능
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();  // 로그로 예외를 출력
        }

        return ResponseEntity.ok(positionAndCrew);
    }
}