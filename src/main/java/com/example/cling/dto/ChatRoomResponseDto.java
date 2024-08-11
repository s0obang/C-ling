package com.example.cling.dto;

import com.example.cling.entity.ChatEntity;
import com.example.cling.entity.RoomEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatRoomResponseDto {

    private List<ChatEntityResponseDto> chatEntities;
    private Long roomId;

    @Getter
    @Builder
    public static class ChatEntityResponseDto {
        private Long chatId;
        private String sender;
        private String message;
        private byte[] imageBytes;
        private LocalDateTime sendDate;
    }

}
