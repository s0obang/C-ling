package com.example.cling.dto;

import com.example.cling.entity.Notice;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {
    private int id;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Content cannot be blank")
    private String content;

    public static NoticeDto toDto(Notice notice) {
        return new NoticeDto(
                notice.getId(),
                notice.getTitle(),
                notice.getContent());
    }
}
