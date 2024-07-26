package com.example.cling.service;

import com.example.cling.dto.SignUpRequestDto;
import com.example.cling.entity.ProfileImage;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.ProfileImageRepository;
import com.example.cling.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileImageService profileImageService;
    private final PasswordEncoder passwordEncoder;

    @Value("${default.image.url}")
    private String defaultImageUrl;

    public UserEntity registerUser(SignUpRequestDto signUpRequestDto) {
        UserEntity user = new UserEntity();
        user.setName(signUpRequestDto.getName());
        user.setStudentId(signUpRequestDto.getStudentId());
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));

        // 기본 프로필 이미지 설정
        ProfileImage profileImage = new ProfileImage();
        profileImage.setUrl(defaultImageUrl);
        profileImage.setUser(user);
        user.setProfileImage(profileImage);

        return userRepository.save(user);
    }

    public void updateProfileImage(String studentId, MultipartFile file) {
        profileImageService.upload(file, studentId);
    }

    public boolean loginUser(String studentId, String password) {
        UserEntity user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return passwordEncoder.matches(password, user.getPassword());
    }
}