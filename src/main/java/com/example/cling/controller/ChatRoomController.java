package com.example.cling.controller;

import com.example.cling.dto.ChatRoomResponseDto;
import com.example.cling.entity.ChatEntity;
import com.example.cling.entity.RoomEntity;
import com.example.cling.entity.UserEntity;
import com.example.cling.service.ChatService;
import com.example.cling.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatService chatService;
    private final UserService userService;
    private final WebClient.Builder webClientBuilder;

    //채팅방 참여
    @GetMapping("/{roomId}")
    public ChatRoomResponseDto joinRoom(@PathVariable(required = false) Long roomId) {

        List<ChatEntity> chatList = chatService.findAllChatByRoomId(chatService.findByRoomId(roomId));

        return ChatRoomResponseDto.builder()
                .roomEntity(chatService.findByRoomId(roomId))
                .roomId(roomId)
                .chatEntities(chatList)
                .build();
    }
    @GetMapping("/roomMapping")
    public ResponseEntity<Void> roomMapper(@AuthenticationPrincipal UserEntity userEntity, @RequestParam String id2) {
        Long roomId = chatService.findRoomByRoomName(chatService.roomNameMaker(userEntity.getStudentId(), id2)).getRoomId();
        String url = "http://localhost:1234/" + roomId;  // URL은 실제 서버 주소에 맞게 조정해야 합니다

        WebClient webClient = webClientBuilder.build();

        webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ChatRoomResponseDto.class)
                .subscribe();

        return ResponseEntity.noContent().build();
    }

//채팅방 등록(생성하는거)
    @PostMapping("/room")
    public RoomEntity createRoom(@AuthenticationPrincipal UserEntity userEntity, @RequestParam String id2) {

        return chatService.createRoom(userService.getUserByStudentId(userEntity.getStudentId()).getStudentId(), id2);

    }
}
