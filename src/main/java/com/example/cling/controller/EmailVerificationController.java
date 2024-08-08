package com.example.cling.controller;

import com.example.cling.dto.EmailCheckDto;
import com.example.cling.service.MailSendService;
import com.example.cling.service.RedisUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class EmailVerificationController {

    private final MailSendService mailSendService;
    private final RedisUtil redisUtil;

    @PostMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestBody @Valid EmailCheckDto emailCheckDto) {
        String email = emailCheckDto.getEmail();
        String authNum = emailCheckDto.getAuthNum();

        // 입력 값 검증
        if (email == null || email.isEmpty() || authNum == null || authNum.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"message\": \"이메일 또는 인증 번호가 잘못되었습니다.\", \"status\": 400}");
        }

        // Redis에서 인증 번호 확인
        String redisAuthNum = redisUtil.getData(email);

        if (redisAuthNum == null || !redisAuthNum.equals(authNum)) {
            return ResponseEntity.badRequest().body("{\"message\": \"인증 번호가 유효하지 않습니다.\", \"status\": 400}");
        }

        return ResponseEntity.ok("{\"message\": \"인증번호가 확인되었습니다.\", \"status\": 200}");
    }
}
