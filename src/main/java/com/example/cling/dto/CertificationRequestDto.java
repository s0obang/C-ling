package com.example.cling.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificationRequestDto {
    private String name;
    private String major;
    private String studentId;
    private String role; // 직책
    private MultipartFile certificationImage; // 인증 이미지
}
