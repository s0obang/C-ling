package com.example.cling.dto;

import com.example.cling.entity.Application;
import com.example.cling.entity.Attachment;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDto {
    private int id;
    @NotBlank(message = "recruitingDepartment cannot be blank")
    private String recruitingDepartment;
    @NotBlank(message = "studentId cannot be blank")
    private String studentId;
    @NotBlank(message = "studentName cannot be blank")
    private String studentName;
    @NotBlank(message = "application cannot be blank")
    private List<AttachmentDto> application;
    private boolean firstResult;
    private boolean secondResult;




    public static ApplicationDto toDto(Application savedApplication) {
        List<AttachmentDto> attachmentDtoList = savedApplication.getApplication().stream()
                .map(attachment -> new AttachmentDto(
                        attachment.getId(),
                        attachment.getOriginAttachmentName(),
                        attachment.getAttachmentUrl()
                ))
                .collect(Collectors.toList());

        return new ApplicationDto(
                savedApplication.getId(),
                savedApplication.getRecruitingDepartment(),
                savedApplication.getStudentId(),
                savedApplication.getStudentName(),
                attachmentDtoList,
                savedApplication.getFirstResult(),
                savedApplication.getSecondResult()
        );
    }
}
