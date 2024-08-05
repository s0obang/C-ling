package com.example.cling.controller;

import com.example.cling.dto.LoginRequest;
import com.example.cling.dto.SignUpRequestDto;
import com.example.cling.entity.UserEntity;
import com.example.cling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@ModelAttribute SignUpRequestDto signUpRequestDto) {
        try {
            UserEntity user = userService.registerUser(signUpRequestDto);

            if (signUpRequestDto.getProfileImage() != null && !signUpRequestDto.getProfileImage().isEmpty()) {
                userService.updateProfileImage(user.getStudentId(), signUpRequestDto.getProfileImage());
            }

            return ResponseEntity.ok("{\"message\": \"회원가입 성공\", \"status\": 200}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"회원가입 실패\", \"status\": 400}");
        }
    }

    @PostMapping("/uploadProfileImage")
    public ResponseEntity<String> uploadProfileImage(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("studentId") String studentId) {
        try {
            userService.updateProfileImage(studentId, file);
            return ResponseEntity.ok("{\"message\": \"이미지 업로드 성공\", \"status\": 200}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"이미지 업로드 실패\", \"status\": 400}");
        }
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
        session.invalidate();
        return ResponseEntity.ok("{\"message\": \"로그아웃 성공\", \"status\": 200}");
    }
}
