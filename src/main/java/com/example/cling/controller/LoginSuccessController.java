package com.example.cling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginSuccessController {

    @GetMapping("/login_success")
    public ResponseEntity<String> showLoginSuccessPage() {
        return ResponseEntity.ok("Welcome! 로그인 성공. 크링하러가기 : <a href='/'>here</a>");
    }
}