package com.example.cling.dto;

import com.example.cling.entity.Image;
import com.example.cling.entity.Notice;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<Image> images; //dto 대신 엔티티 씀
    @NotBlank(message = "Date cannot be blank")
    private LocalDateTime createdDate;

    public static NoticeDto toDto(Notice notice) {
        return new NoticeDto(
                notice.getId(),
                notice.getUserId(),
                notice.getTitle(),
                notice.getContent(),
                notice.getImages(),
                notice.getCreatedDate());
    }
}
