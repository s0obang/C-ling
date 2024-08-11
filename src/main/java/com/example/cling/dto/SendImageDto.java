package com.example.cling.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SendImageDto {
    private Long roomId;
    private String sender;
    private MultipartFile imageFile;
}