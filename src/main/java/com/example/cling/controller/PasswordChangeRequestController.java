package com.example.cling.controller;

import com.example.cling.dto.PasswordChangeRequestDto;
import com.example.cling.entity.UserEntity;
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
public class PasswordChangeRequestController {

    private final UserService userService;
    private final PasswordChangeMailService passwordChangeMailService;

    @PostMapping("/request-password-change")
    public ResponseEntity<String> requestPasswordChange(@RequestBody @Valid PasswordChangeRequestDto passwordChangeRequestDto) {
        // 학번과 이름으로 사용자 조회
        UserEntity user = userService.getUserByStudentIdAndName(passwordChangeRequestDto.getStudentId(), passwordChangeRequestDto.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"사용자를 찾을 수 없습니다.\", \"status\": 404}");
        }
        // 이메일로 인증번호 발송
        String authNumber = passwordChangeMailService.sendPasswordChangeAuth(user.getEmail());
        return ResponseEntity.ok("{\"message\": \"인증번호가 이메일로 전송되었습니다.\", \"status\": 200, \"authNumber\": \"" + authNumber + "\"}");
    }
}
