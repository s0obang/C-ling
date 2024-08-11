package com.example.cling.controller;

import com.example.cling.dto.MyPageResponseDto;
import com.example.cling.service.UserService;
import com.example.cling.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/my-page")
@RequiredArgsConstructor
public class PositionAndCrewController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping("/position-crew-info")
    public ResponseEntity<MyPageResponseDto> getPositionAndCrew(@RequestHeader("Authorization") String token) {
        String studentId = extractStudentIdFromToken(token);
        MyPageResponseDto responseDto = userService.getUserPositionAndCrew(studentId);
        return ResponseEntity.ok(responseDto);
    }

    private String extractStudentIdFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        Claims claims = jwtUtil.extractClaims(token);
        return claims.getSubject();
    }
}
