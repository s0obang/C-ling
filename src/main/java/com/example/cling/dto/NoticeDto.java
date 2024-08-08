package com.example.cling.dto;

import com.example.cling.entity.Notice;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {
    private int id;
    @NotBlank(message = "User cannot be blank")
    private String userId;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Content cannot be blank")
    private String content;
    @NotBlank(message = "Images cannot be blank")
    private List<AttachmentDto> images;
    @NotBlank(message = "Date cannot be blank")
    private String createdDate;

    public static NoticeDto toDto(Notice notice) {
        List<AttachmentDto> imageDtoList = notice.getImages().stream()
                .map(image -> new AttachmentDto(
                        image.getId(),
                        image.getOriginAttachmentName(),
                        image.getAttachmentUrl()
                ))
                .collect(Collectors.toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String createdDate = notice.getCreatedDate().format(formatter);

        return new NoticeDto(
                notice.getId(),
                notice.getUserId(),
                notice.getTitle(),
                notice.getContent(),
                imageDtoList,
                createdDate
        );
    }
}