package com.example.cling.controller;

import com.example.cling.dto.MyPageResponseDto;
import com.example.cling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/my-page")
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;

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
        return ResponseEntity.ok(positionAndCrew);
    }
}