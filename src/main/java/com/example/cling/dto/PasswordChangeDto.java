package com.example.cling.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
public class PasswordChangeDto {
    @NotEmpty
    @Email(message = "유효한 이메일 주소를 입력해 주세요")
    private String email;

    @NotEmpty(message = "인증 번호를 입력해 주세요")
    private String authNum;

    @NotEmpty(message = "새 비밀번호를 입력해 주세요")
    private String newPassword;

    @NotEmpty(message = "비밀번호 확인을 입력해 주세요")
    private String confirmPassword;

    // 비밀번호와 비밀번호 확인이 일치하는지 확인
    public boolean isPasswordMatching() {
        return newPassword != null && newPassword.equals(confirmPassword);
    }
}
