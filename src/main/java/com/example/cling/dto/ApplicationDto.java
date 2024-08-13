package com.example.cling.dto;

import com.example.cling.entity.Application;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
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
    @NotBlank(message = "firstResult cannot be blank")
    private Boolean firstResult;
    @NotBlank(message = "secondResult application cannot be blank")
    private Boolean secondResult;
    @NotBlank(message = "recruitment_id cannot be blank")
    private int recruitment_id;
    @NotBlank(message = "view cannot be blank")
    private boolean view;



    public static ApplicationDto toDto(Application savedApplication) throws IOException {
        List<AttachmentDto> attachmentDtoList = savedApplication.getApplication().stream()
                .map(attachment -> {
                    try {
                        return new AttachmentDto(
                                attachment.getId(),
                                attachment.getOriginAttachmentName(),
                                attachment.getAttachmentUrl(),
                                AttachmentDto.getAttachmentBytes(attachment.getAttachmentPath())
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        return new ApplicationDto(
                savedApplication.getId(),
                savedApplication.getRecruitingDepartment(),
                savedApplication.getStudentId(),
                savedApplication.getStudentName(),
                attachmentDtoList,
                savedApplication.getFirstResult(),
                savedApplication.getSecondResult(),
                savedApplication.getRecruitment().getId(),
                savedApplication.isView()
        );
    }
}
