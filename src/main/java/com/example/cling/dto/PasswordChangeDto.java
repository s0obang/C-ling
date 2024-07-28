package com.example.cling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeDto {
    private String email;
    private String authNum;
    private String newPassword;
    private String confirmPassword;

    // 비밀번호와 비밀번호 확인이 일치하는지 확인
    public boolean isPasswordMatching() {
        return newPassword != null && newPassword.equals(confirmPassword);
    }
}
