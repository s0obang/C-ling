package com.example.cling.dto;

import com.example.cling.entity.ChatEntity;
import com.example.cling.entity.RoomEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatRoomResponseDto {

    private List<ChatEntity> chatEntities;
    private Long roomId;

}
