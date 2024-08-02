package com.example.cling.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResult {
    private String errorCode;
    private String errorMessage;
}
