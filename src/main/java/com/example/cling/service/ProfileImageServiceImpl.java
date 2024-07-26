package com.example.cling.service;

import com.example.cling.dto.ProfileImageResponseDto;
import com.example.cling.entity.ProfileImage;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.ProfileImageRepository;
import com.example.cling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileImageServiceImpl implements ProfileImageService {
    private final UserRepository userRepository;
    private final ProfileImageRepository profileImageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Value("${default.image.url}")
    private String defaultImageUrl;

    @Override
    public void upload(MultipartFile file, String studentId) {
        try {
            // 1. 사용자 찾기
            UserEntity user = userRepository.findByStudentId(studentId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // 2. 파일 이름 생성 및 저장 경로 설정
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File destinationFile = new File(uploadFolder + fileName);

            // 3. 파일을 저장
            file.transferTo(destinationFile);

            // 4. 프로필 이미지 정보를 업데이트
            ProfileImage profileImage = profileImageRepository.findByUser(user)
                    .orElse(ProfileImage.builder().user(user).build());

            profileImage.setUrl("/profileImages/" + fileName);
            profileImage.setUser(user); // 연관관계 설정

            profileImageRepository.save(profileImage);

            // 사용자 엔티티에 프로필 이미지 설정
            user.setProfileImage(profileImage);
            userRepository.save(user);

        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
    }

    @Override
    public ProfileImageResponseDto findImage(String studentId) {
        Optional<UserEntity> userOptional = userRepository.findByStudentId(studentId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        UserEntity user = userOptional.get();

        Optional<ProfileImage> profileImageOptional = profileImageRepository.findByUser(user);

        if (profileImageOptional.isPresent()) {
            return ProfileImageResponseDto.builder()
                    .url(profileImageOptional.get().getUrl())
                    .build();
        } else {
            return ProfileImageResponseDto.builder()
                    .url(defaultImageUrl)
                    .build();
        }
    }
}