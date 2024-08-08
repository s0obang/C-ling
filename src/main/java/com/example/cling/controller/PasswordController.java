package com.example.cling.controller;

import com.example.cling.dto.PasswordChangeDto;
import com.example.cling.service.MailSendService;
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
    private final MailSendService mailSendService;

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid PasswordChangeDto passwordChangeDto) {
        // 인증번호 확인
        boolean isAuthValid = mailSendService.checkAuthNum(passwordChangeDto.getEmail(), passwordChangeDto.getAuthNum());
        if (!isAuthValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 번호가 유효하지 않습니다.");
        }
        // 비밀번호와 확인 비밀번호가 일치하는지 확인
        if (!passwordChangeDto.isPasswordMatching()) {
            return ResponseEntity.badRequest().body("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
        // 비밀번호 업데이트
        userService.updatePassword(passwordChangeDto.getEmail(), passwordChangeDto.getNewPassword());
        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
    }
}
