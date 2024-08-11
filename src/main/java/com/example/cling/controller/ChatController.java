package com.example.cling.controller;

import com.example.cling.dto.ChatDto;
import com.example.cling.dto.ChatRequestDto;
import com.example.cling.dto.SendImageDto;
import com.example.cling.entity.ChatEntity;
import com.example.cling.service.ChatService;
import com.example.cling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;

    @MessageMapping("/room/{roomId}/chat") //여기로 전송되면 메서드 호출 -> WebSocketConfig prefixes 에서 적용한건 앞에 생략
    @SendTo("/sub/room/{roomId}")   //구독하고 있는 장소로 메시지 전송 (목적지)  -> WebSocketConfig Broker 에서 적용한건 앞에 붙어줘야됨
    public ChatDto chat(@DestinationVariable("roomId") Long roomId, ChatRequestDto message) {
        //채팅 저장
        ChatEntity chat = chatService.createChat(roomId, message.getSender(), message.getMessage());
        return ChatDto.builder()
                .roomId(roomId)
                .sender(chat.getSender())
                .message(chat.getMessage())
                .sendDate(chat.getSendDate())
                .build();
    }

    @MessageMapping("/room/{roomId}/image")
    @SendTo("/sub/room/{roomId}")
    public ChatDto sendImage(@DestinationVariable("roomId") Long roomId, SendImageDto sendImageDto) throws IOException {
        // 이미지 저장 및 채팅 저장
        ChatEntity chat = chatService.sendImage(sendImageDto);
        byte[] imageBytes = chatService.getImageBytes(chat.getFileUrl());

        return ChatDto.builder()
                .roomId(roomId)
                .sender(chat.getSender())
                .message("Image sent") // 메시지에 이미지 전송 사실을 표기
                .imageBytes(imageBytes)
                .sendDate(chat.getSendDate())
                .build();
    }
}
