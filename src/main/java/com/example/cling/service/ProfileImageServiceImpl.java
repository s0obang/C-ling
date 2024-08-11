package com.example.cling.service;

import com.example.cling.dto.ProfileImageResponseDto;
import com.example.cling.entity.ProfileImage;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.ProfileImageRepository;
import com.example.cling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
            UserEntity user = userRepository.findByStudentId(studentId)
                    .orElseThrow(() -> new RuntimeException("User not found with studentId: " + studentId));

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File destinationFile = new File(uploadFolder + "/" + fileName);

            // 파일 저장
            file.transferTo(destinationFile);

            ProfileImage profileImage = profileImageRepository.findByUser(user)
                    .orElse(ProfileImage.builder().user(user).build());

            profileImage.setUrl("/profileImages/" + fileName);
            profileImage.setUser(user);

            profileImageRepository.save(profileImage);

            user.setProfileImage(profileImage);
            userRepository.save(user);

            log.info("Profile image uploaded successfully for studentId: " + studentId);

        } catch (IOException e) {
            log.error("File upload failed for studentId: " + studentId, e);
            throw new RuntimeException("File upload failed", e);
        }
    }

    @Override
    public ProfileImageResponseDto findImage(String studentId) {
        Optional<UserEntity> userOptional = userRepository.findByStudentId(studentId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with studentId: " + studentId);
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
