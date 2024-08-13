package com.example.cling.repository;

import com.example.cling.entity.PositionRequest;
import com.example.cling.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRequestRepository extends JpaRepository<PositionRequest, Long> {
    List<PositionRequest> findByUser(UserEntity user);
}