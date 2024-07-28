package com.example.cling.dto;

import com.example.cling.entity.Notice;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeCreateDto {
    @NotBlank(message = "User cannot be blank")
    private String userId;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Content cannot be blank")
    private String content;

}