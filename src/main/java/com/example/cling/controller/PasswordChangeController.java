package com.example.cling.controller;

import com.example.cling.dto.PasswordChangeDto;
import com.example.cling.dto.PasswordChangeRequestDto;
import com.example.cling.entity.UserEntity;
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
public class PasswordChangeController {

    private final UserService userService;
    private final MailSendService mailSendService;

    @PostMapping("/request-password-change")
    public ResponseEntity<String> requestPasswordChange(@RequestBody @Valid PasswordChangeRequestDto passwordChangeRequestDto) {
        // 학번과 이름으로 사용자 조회
        UserEntity user = userService.getUserByStudentIdAndName(passwordChangeRequestDto.getStudentId(), passwordChangeRequestDto.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"사용자를 찾을 수 없습니다.\", \"status\": 404}");
        }
        // 이메일로 인증번호 발송
        String authNumber = mailSendService.sendPasswordChangeAuth(user.getEmail());
        return ResponseEntity.ok("{\"message\": \"인증번호가 이메일로 전송되었습니다.\", \"status\": 200, \"authNumber\": \"" + authNumber + "\"}");
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid PasswordChangeDto passwordChangeDto) {
        // 인증번호 확인
        boolean isAuthValid = mailSendService.CheckAuthNum(passwordChangeDto.getEmail(), passwordChangeDto.getAuthNum());
        if (!isAuthValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"인증 번호가 유효하지 않습니다.\", \"status\": 401}");
        }
        // 비밀번호 일치 여부 확인
        if (!passwordChangeDto.isPasswordMatching()) {
            return ResponseEntity.badRequest().body("{\"message\": \"비밀번호와 비밀번호 확인이 일치하지 않습니다.\", \"status\": 400}");
        }
        // 비밀번호 변경
        userService.updatePassword(passwordChangeDto.getEmail(), passwordChangeDto.getNewPassword());
        return ResponseEntity.ok("{\"message\": \"비밀번호가 성공적으로 변경되었습니다.\", \"status\": 200}");
    }
}
