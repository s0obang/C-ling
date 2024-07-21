package com.example.cling.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
@Getter
@Setter
public class EmailRequestDto {
    @Email//이메일 검증
    @NotEmpty(message = "이메일을 입력해 주세요")
    private String email;
}