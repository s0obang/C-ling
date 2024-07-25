package com.example.cling.controller;

import com.example.cling.dto.LoginRequest;
import com.example.cling.dto.SignUpRequestDto;
import com.example.cling.entity.UserEntity;
import com.example.cling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> registerUser(@RequestBody SignUpRequestDto signUpRequestDto) {
        UserEntity user = userService.registerUser(signUpRequestDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isLoggedIn = userService.loginUser(loginRequest.getStudentId(), loginRequest.getPassword());

        if (isLoggedIn) {
            return ResponseEntity.ok("{\"message\": \"로그인 성공\", \"status\": 200}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"로그인 실패\", \"status\": 401}");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok("{\"message\": \"로그아웃 성공\", \"status\": 200}");
    }

    @GetMapping("/login")
    public ResponseEntity<String> getLoginPageUrl() {
        return ResponseEntity.ok("{\"loginUrl\": \"/login.html\"}");
    }
}
