package com.example.cling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageResponseDto {
    private String name;
    private String studentId;
    private String profileImageUrl;
    private String major;

    public MyPageResponseDto() {
    }

    public MyPageResponseDto(String name, String studentId, String profileImageUrl, String major) {
        this.name = name;
        this.studentId = studentId;
        this.profileImageUrl = profileImageUrl;
        this.major = major;
    }
}