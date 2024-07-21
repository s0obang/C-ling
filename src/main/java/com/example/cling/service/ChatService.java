package com.example.cling.service;


import com.example.cling.entity.RoomEntity;
import com.example.cling.repository.ChatRepository;
import com.example.cling.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final RoomRepository roomRepository;

    //채팅방 찾아오는거
    public RoomEntity findRoomByRoomName(String id1, String id2) {
        String roomName;
        if (id1.compareTo(id2) <= 0) {
            roomName = id1 + id2;
        } else {
            roomName= id2 + id1;
        }
        return roomRepository.findByRoomName(roomName);
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
}
