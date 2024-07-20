package com.example.cling.repository;

import com.example.cling.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    public RoomEntity findByRoomName(String name);
}
