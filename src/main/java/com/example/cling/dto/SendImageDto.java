package com.example.cling.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendImageDto {
    //private Long roomId;
    private String sender;
    private MultipartFile imageFile;


}