package com.example.cling.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class UpdateUserDto {

    private String name;
    private String email;
    private String major;
    private MultipartFile profileImage;

    @Builder
    public UpdateUserDto(String name, String email, String major, MultipartFile profileImage) {
        this.name = name;
        this.email = email;
        this.major = major;
        this.profileImage = profileImage;
    }
}
