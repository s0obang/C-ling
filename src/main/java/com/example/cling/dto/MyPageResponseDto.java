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
    private String position;

    public MyPageResponseDto() {
    }

    public MyPageResponseDto(String name, String studentId, String profileImageUrl, String major, String position) {
        this.name = name;
        this.studentId = studentId;
        this.profileImageUrl = profileImageUrl;
        this.major = major;
        this.position=position;
    }
}
