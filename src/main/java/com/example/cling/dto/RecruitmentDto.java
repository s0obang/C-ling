package com.example.cling.dto;

import com.example.cling.entity.Recruitment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentDto {
    private int id;
    @NotBlank(message = "RecruitingDepartment cannot be blank")
    private String RecruitingDepartment;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Content cannot be blank")
    private String content;
    @NotBlank(message = "Images cannot be blank")
    private List<AttachmentDto> images;
    @NotBlank(message = "Files cannot be blank")
    private List<AttachmentDto> files;

    public static RecruitmentDto toDto(Recruitment recruitment) {
        List<AttachmentDto> imageDtoList = recruitment.getImages() != null ? recruitment.getImages().stream()
                .map(image -> new AttachmentDto(
                        image.getOriginAttachmentName(),
                        image.getAttachmentName(),
                        image.getAttachmentUrl(),
                        image.getFileType()
                ))
                .collect(Collectors.toList()) : Collections.emptyList();

        List<AttachmentDto> fileDtoList = recruitment.getFiles() != null ? recruitment.getFiles().stream()
                .map(file -> new AttachmentDto(
                        file.getOriginAttachmentName(),
                        file.getAttachmentName(),
                        file.getAttachmentUrl(),
                        file.getFileType()
                ))
                .collect(Collectors.toList()) : Collections.emptyList();

        return new RecruitmentDto(
                recruitment.getId(),
                recruitment.getRecruitingDepartment(),
                recruitment.getTitle(),
                recruitment.getContent(),
                imageDtoList,
                fileDtoList
        );
    }

}
