package com.example.cling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<String> getLoginPageUrl() {
        // 로그인 페이지 URL을 JSON 형식으로 반환
        return ResponseEntity.ok("{\"loginUrl\": \"/login.html\"}");
    }
}
