package com.example.cling.repository;

import com.example.cling.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByStudentId(String studentId);

    // 이메일로 사용자 조회
    Optional<UserEntity> findByEmail(String email);
}
