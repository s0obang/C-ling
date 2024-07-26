package com.example.cling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String studentId;
    private String name;
    private String email;
    private String profileImageUrl;
}