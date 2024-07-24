package com.example.cling.service;


import com.example.cling.entity.ChatEntity;
import com.example.cling.entity.RoomEntity;
import com.example.cling.repository.ChatRepository;
import com.example.cling.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final RoomRepository roomRepository;

    //채팅방 찾아오는거
    /**
    public RoomEntity findRoomByRoomName(String id1, String id2) {
        String roomName;
        if (id1.compareTo(id2) <= 0) {
            roomName = id1 + id2;
        } else {
            roomName= id2 + id1;
        }
        return roomRepository.findByRoomName(roomName);
    }*/

     public RoomEntity findRoomByRoomName(String roomName) {
        return roomRepository.findByRoomName(roomName);
     }

    public String roomNameMaker(String id1, String id2) {
        String roomName;
        if (id1.compareTo(id2) <= 0) {
            roomName = id1 + id2;
        } else {
            roomName= id2 + id1;
        }
        return roomName;
    }
    public RoomEntity findByRoomId(Long roomId){
        return roomRepository.findByRoomId(roomId);
    }
    //채팅방 만드는거
    public RoomEntity createRoom(String id1, String id2) {
        String roomName;
        if (id1.compareTo(id2) <= 0) {
             roomName = id1 + id2;
        } else {
            roomName= id2 + id1;
        }
        return roomRepository.save(
                RoomEntity.builder()
                        .roomName(roomName)
                        .build()
        );
    }
    public ChatEntity createChat(Long roomId, String sender, String message) {
        RoomEntity room = roomRepository.findById(roomId).orElseThrow();  //방 찾기 -> 없는 방일 경우 여기서 예외처리
        return chatRepository.save(ChatEntity.builder()
                .roomId(room)
                .sender(sender)
                .message(message)
                .build()
        );
    }
    public List<ChatEntity> findAllChatByRoomId(RoomEntity roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }
}
