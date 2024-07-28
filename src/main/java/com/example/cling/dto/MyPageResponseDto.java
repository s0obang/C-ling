package com.example.cling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageResponseDto {
    private String name;
    private String studentId;
    private String profileImageUrl;

    public MyPageResponseDto() {
    }

    public MyPageResponseDto(String name, String studentId, String profileImageUrl) {
        this.name = name;
        this.studentId = studentId;
        this.profileImageUrl = profileImageUrl;
    }
}
