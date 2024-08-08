package com.example.cling.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeDto {

    @NotEmpty(message = "인증 번호를 입력해 주세요")
    private String authNum;

    @NotEmpty(message = "새 비밀번호를 입력해 주세요")
    private String newPassword;

    @NotEmpty(message = "비밀번호 확인을 입력해 주세요")
    private String confirmPassword;

    public boolean isPasswordMatching() {
        return newPassword != null && newPassword.equals(confirmPassword);
    }
}
