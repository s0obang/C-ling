package com.example.cling.controller;

import com.example.cling.dto.PasswordChangeDto;
import com.example.cling.service.PasswordChangeMailService;
import com.example.cling.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class PasswordController {

    private final UserService userService;
    private final PasswordChangeMailService passwordChangeMailService;

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid PasswordChangeDto passwordChangeDto) {
        // 인증번호 확인
        String email = passwordChangeMailService.getEmailByAuthNum(passwordChangeDto.getAuthNum());
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"인증 번호가 유효하지 않습니다.\", \"status\": 401}");
        }
        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (!passwordChangeDto.isPasswordMatching()) {
            return ResponseEntity.badRequest().body("{\"message\": \"비밀번호와 비밀번호 확인이 일치하지 않습니다.\", \"status\": 400}");
        }
        // 비밀번호 업데이트
        userService.updatePassword(email, passwordChangeDto.getNewPassword());
        return ResponseEntity.ok("{\"message\": \"비밀번호가 성공적으로 변경되었습니다.\", \"status\": 200}");
    }
}