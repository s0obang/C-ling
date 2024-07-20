package com.example.cling.repository;

import com.example.cling.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    public ChatEntity findByChatId(Long chatId);
}
