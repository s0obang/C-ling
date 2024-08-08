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


    //기존 채팅 내용 불러오기
    @GetMapping("/oldChat/{roomId}")
    public ResponseEntity<ChatRoomResponseDto> getOldChat(@PathVariable(required = false) Long roomId) {
        List<ChatEntity> chatList = chatService.findAllChatByRoomId(chatService.findByRoomId(roomId));
        ChatRoomResponseDto response = ChatRoomResponseDto.builder()
                .roomId(roomId)
                .chatEntities(chatList)
                .build();
        return ResponseEntity.ok(response);
    }

    //채팅방 들어가기(매칭 페이지에서 클릭했을 때
    @GetMapping("/roomEnter")
    public ResponseEntity<RoomEntity> roomMapper(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String id2) {
        RoomEntity roomEntity = chatService.findRoomByRoomName(chatService.roomNameMaker(userDetails.getUsername(), id2));
        return ResponseEntity.ok(roomEntity);
    }
    //채팅방 참여하면 roomEntity을 return함 topic은 roomEntity의 Id

}
