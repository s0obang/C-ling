package com.example.cling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String profileImage;
    private String name;
    private String studentId;
    private String email;
    private String password;
}