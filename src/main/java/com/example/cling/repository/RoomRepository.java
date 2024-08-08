package com.example.cling.repository;

import com.example.cling.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    public Optional<RoomEntity> findByRoomName(String roomName);
    public RoomEntity findByRoomId(Long roomId);
}
