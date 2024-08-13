package com.example.cling.controller;

import com.example.cling.dto.ChatDto;
import com.example.cling.dto.ChatRequestDto;
import com.example.cling.dto.SendImageDto;
import com.example.cling.entity.ChatEntity;
import com.example.cling.service.ChatService;
import com.example.cling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;
    private final SimpMessagingTemplate messagingTemplate;

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
/*
    @MessageMapping("/room/{roomId}/image")
    @SendTo("/sub/room/{roomId}")
    public ChatDto sendImage(@DestinationVariable("roomId") Long roomId, SendImageDto sendImageDto) throws IOException {
        // 이미지 저장 및 채팅 저장
        ChatEntity chat = chatService.sendImage(roomId, sendImageDto);
        byte[] imageBytes = chatService.getImageBytes(chat.getFileUrl());

        return ChatDto.builder()
                .roomId(roomId)
                .sender(chat.getSender())
                .message("Image sent") // 메시지에 이미지 전송 사실을 표기
                .imageBytes(imageBytes)
                .sendDate(chat.getSendDate())
                .build();
    }

 */

    @PostMapping("/room/{roomId}/sendImage")
    public ResponseEntity<ChatDto> uploadImage(@RequestParam("sender") String sender,
                                               @RequestParam("imageFile") MultipartFile imageFile,
                                               @PathVariable Long roomId) throws IOException {
        // 이미지 파일 처리 및 저장
        SendImageDto sendImageDto = new SendImageDto();
        sendImageDto.setSender(sender);
        sendImageDto.setImageFile(imageFile);

        ChatEntity chat = chatService.sendImage(roomId, sendImageDto);

        // 메시지 퍼블리싱: 직접 @MessageMapping 메서드를 호출하지 않고, messagingTemplate을 사용해 퍼블리싱
        byte[] imageBytes = chatService.getImageBytes(chat.getFileUrl());

        ChatDto chatDto = ChatDto.builder()
                .roomId(roomId)
                .sender(chat.getSender())
                .message("Image sent") // 메시지에 이미지 전송 사실을 표기
                .imageBytes(imageBytes)
                .sendDate(chat.getSendDate())
                .build();

        // STOMP를 통해 클라이언트들에게 메시지 퍼블리싱
        messagingTemplate.convertAndSend("/sub/room/" + roomId, chatDto);

        return ResponseEntity.ok(chatDto);
    }
}
