package com.example.cling.dto;

import com.example.cling.entity.Notice;
import com.example.cling.entity.ProfileImage;
import com.example.cling.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {
    private String studentId;
    private String name;
    private String email;
    private String profileImageUrl;
    private String major;

    public static UserResponseDto toDto(UserEntity user) {
        return new UserResponseDto(
                user.getStudentId(),
                user.getName(),
                user.getEmail(),
                user.getProfileImage().getUrl(),
                user.getMajor()
        );
    }
}
