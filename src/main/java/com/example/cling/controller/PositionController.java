package com.example.cling.controller;

import com.example.cling.dto.ApprovePositionDto;
import com.example.cling.dto.CrewRequestDto;
import com.example.cling.dto.PositionRequestDto;
import com.example.cling.service.CrewService;
import com.example.cling.service.PositionRequestService;
import com.example.cling.service.UserService;
import com.example.cling.util.JwtUtil;
import io.jsonwebtoken.Claims;
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
    private final CrewService crewService;
    private final UserService userService;
    private final JwtUtil jwtUtil; // JwtUtil 인스턴스 주입

    @PostMapping("/request")
    public ResponseEntity<String> requestPosition(
            @RequestParam("name") String name,
            @RequestParam("major") String major,
            @RequestParam("studentId") String studentId,
            @RequestParam("position") String position,
            @RequestParam(value = "crewName", required = false) String crewName,  // 추가된 파라미터
            @RequestParam(value = "authenticationImage", required = false) MultipartFile authenticationImage) {

        PositionRequestDto positionRequestDto = PositionRequestDto.builder()
                .name(name)
                .major(major)
                .studentId(studentId)
                .position(position)
                .crewName(crewName)
                .authenticationImage(authenticationImage)
                .build();

        try {
            positionRequestService.processPositionRequest(positionRequestDto);
            return ResponseEntity.ok("{\"message\": \"직책 인증 요청이 성공적으로 처리되었습니다.\", \"status\": 200}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"" + e.getMessage() + "\", \"status\": 400}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"직책 인증 요청 처리 실패: " + e.getMessage() + "\", \"status\": 500}");
        }
    }

    @PostMapping("/approve")
    public ResponseEntity<String> approvePosition(@RequestBody ApprovePositionDto approvePositionDto) {
        try {
            userService.updatePosition(approvePositionDto.getStudentId(), approvePositionDto.getPosition());
            crewService.addCrew(approvePositionDto.getStudentId(), new CrewRequestDto(
                    approvePositionDto.getPosition(), approvePositionDto.getCrewName()
            ));
            return ResponseEntity.ok("{\"message\": \"직책과 크루명이 성공적으로 승인되었습니다.\", \"status\": 200}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"직책 및 크루명 승인 처리 실패: " + e.getMessage() + "\", \"status\": 500}");
        }
    }


    @PostMapping("/addCrew")
    public ResponseEntity<String> addCrew(
            @RequestHeader("Authorization") String token,
            @RequestBody CrewRequestDto crewRequestDto) {

        String studentId = extractStudentIdFromToken(token);

        try {
            crewService.addCrew(studentId, crewRequestDto);
            return ResponseEntity.ok("{\"message\": \"크루명이 성공적으로 추가되었습니다.\", \"status\": 200}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"크루명 추가 실패: " + e.getMessage() + "\", \"status\": 500}");
        }
    }

    @DeleteMapping("/deleteCrew")
    public ResponseEntity<String> deleteCrew(
            @RequestHeader("Authorization") String token,
            @RequestBody CrewRequestDto crewRequestDto) {

        String studentId = extractStudentIdFromToken(token);
        String crewName = crewRequestDto.getCrewName();

        try {
            crewService.deleteCrew(studentId, crewName);
            return ResponseEntity.ok("{\"message\": \"크루명이 성공적으로 삭제되었습니다.\", \"status\": 200}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"크루명 삭제 실패: " + e.getMessage() + "\", \"status\": 500}");
        }
    }

    private String extractStudentIdFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
        }
        // Use JwtUtil to extract claims from the token
        Claims claims = jwtUtil.extractClaims(token);
        return claims.getSubject(); // Assuming studentId is stored in the 'sub' claim
    }
}
