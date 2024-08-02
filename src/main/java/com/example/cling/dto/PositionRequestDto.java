package com.example.cling.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionRequestDto {
    private String name;
    private String major;
    private String studentId;
    private String position;
    private MultipartFile authenticationImage;
}
