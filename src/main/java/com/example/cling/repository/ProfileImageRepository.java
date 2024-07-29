package com.example.cling.repository;

import com.example.cling.entity.ProfileImage;
import com.example.cling.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    Optional<ProfileImage> findByUser(UserEntity user);
}
