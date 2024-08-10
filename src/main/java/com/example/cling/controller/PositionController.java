package com.example.cling.controller;

import com.example.cling.service.CrewService;
import com.example.cling.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/position")
@RequiredArgsConstructor
public class PositionController {

    private final CrewService crewService;
    private final JwtUtil jwtUtil;

    @PostMapping("/deleteCrew")
    public ResponseEntity<String> deleteCrew(
            @RequestHeader("Authorization") String token,
            @RequestParam("crewName") String crewName) {

        String studentId = extractStudentIdFromToken(token);

        try {
            crewService.deleteCrew(studentId, crewName);
            return ResponseEntity.ok("{\"message\": \"크루명이 성공적으로 삭제되었습니다.\", \"status\": 200}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"" + e.getMessage() + "\", \"status\": 400}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"크루명 삭제 처리 실패: " + e.getMessage() + "\", \"status\": 500}");
        }
    }

    private String extractStudentIdFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
        }
        Claims claims = jwtUtil.extractClaims(token);
        return claims.getSubject(); // Assuming studentId is stored in the 'sub' claim
    }
}
