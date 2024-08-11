package com.example.cling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {
    private Long roomId;
    private String sender;
    private String message;
    private LocalDateTime sendDate;
    private byte[] imageBytes;
}
