package com.example.cling.repository;

import com.example.cling.entity.ChatEntity;
import com.example.cling.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    public ChatEntity findByChatId(Long chatId);
    List<ChatEntity> findAllByRoomId(RoomEntity roomId);
}
