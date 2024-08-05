package com.example.cling.dto;

import com.example.cling.entity.Attachment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationCreateDto {
    @NotBlank(message = "recruitment_id cannot be blank")
    private int recruitment_id;
    @NotBlank(message = "studentId cannot be blank")
    private String studentId;
    @NotBlank(message = "studentName cannot be blank")
    private String studentName;
    @NotBlank(message = "application cannot be blank")
    private List<MultipartFile> application;
}

