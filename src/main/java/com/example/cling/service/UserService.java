package com.example.cling.service;

import com.example.cling.dto.MyPageResponseDto;
import com.example.cling.dto.SignUpRequestDto;
import com.example.cling.entity.ProfileImage;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.ProfileImageRepository;
import com.example.cling.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        user.setMajor(signUpRequestDto.getMajor()); // major 설정

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

    public MyPageResponseDto getUserInfo(String studentId) {
        UserEntity user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 프로필 이미지 URL 가져오기
        String profileImageUrl = user.getProfileImage() != null ? user.getProfileImage().getUrl() : defaultImageUrl;

        return new MyPageResponseDto(user.getName(), user.getStudentId(), profileImageUrl, user.getMajor(), user.getPosition());
    }

    public void updatePassword(String email, String newPassword) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public UserEntity getUserByStudentId(String studentId) {
        return userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with studentId: " + studentId));
    }

    public UserEntity getUserByStudentIdAndName(String studentId, String name) {
        return userRepository.findByStudentIdAndName(studentId, name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with studentId: " + studentId + " and name: " + name));
    }

    public void updatePosition(String studentId, String position) {
        UserEntity user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        user.setPosition(position);  // 직책을 저장하는 필드에 업데이트
        userRepository.save(user);
    }
}
