package com.example.cling.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String name;
    private String studentId;
    private String email;
    private String password;
    private String major;
    private MultipartFile profileImage;
}

