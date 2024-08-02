package com.example.cling.controller;

import com.example.cling.dto.PositionRequestDto;
import com.example.cling.service.PositionRequestService;
import com.example.cling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/position")
@RequiredArgsConstructor
public class PositionController {

    private final PositionRequestService positionRequestService;
    private final UserService userService;

    @PostMapping("/request")
    public ResponseEntity<String> requestPosition(
            @RequestParam("name") String name,
            @RequestParam("major") String major,
            @RequestParam("studentId") String studentId,
            @RequestParam("position") String position,
            @RequestParam(value = "authenticationImage", required = false) MultipartFile authenticationImage) {

        PositionRequestDto positionRequestDto = PositionRequestDto.builder()
                .name(name)
                .major(major)
                .studentId(studentId)
                .position(position)
                .authenticationImage(authenticationImage)
                .build();

        try {
            positionRequestService.processPositionRequest(positionRequestDto);
            return ResponseEntity.ok("{\"message\": \"직책 인증 요청이 성공적으로 처리되었습니다.\", \"status\": 200}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"직책 인증 요청 처리 실패: " + e.getMessage() + "\", \"status\": 500}");
        }
    }

    @PostMapping("/approve")
    public ResponseEntity<String> approvePosition(@RequestParam("studentId") String studentId, @RequestParam("position") String position) {
        try {
            userService.updatePosition(studentId, position);
            return ResponseEntity.ok("{\"message\": \"직책이 성공적으로 승인되었습니다.\", \"status\": 200}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"직책 승인 처리 실패: " + e.getMessage() + "\", \"status\": 500}");
        }
    }
}
