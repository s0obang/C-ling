package com.example.cling.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
public class PasswordChangeRequestDto {

    @NotEmpty(message = "학번을 입력해 주세요")
    private String studentId;

    @NotEmpty(message = "이름을 입력해 주세요")
    private String name;

    @NotEmpty(message = "인증 번호를 입력해 주세요")
    private String authNum;
}
