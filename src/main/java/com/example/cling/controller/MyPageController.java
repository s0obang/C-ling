package com.example.cling.controller;

import com.example.cling.dto.MyPageResponseDto;
import com.example.cling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/my-page")
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;

    @GetMapping("/user-info")
    public ResponseEntity<MyPageResponseDto> getUserInfo(@RequestParam("studentId") String studentId) {
        MyPageResponseDto userInfo = userService.getUserInfo(studentId);

        return ResponseEntity.ok(userInfo);
    }
}