package com.example.cling.controller;

import com.example.cling.dto.SignUpRequestDto;
import com.example.cling.entity.UserEntity;
import com.example.cling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> registerUser(@RequestBody SignUpRequestDto signUpRequestDto) {
        UserEntity user = userService.registerUser(signUpRequestDto);
        return ResponseEntity.ok(user);
    }
}