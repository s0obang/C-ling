package com.example.cling.service;

import com.example.cling.dto.SignUpRequestDto;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity registerUser(SignUpRequestDto signUpRequestDto) {
        UserEntity user = UserEntity.builder()
                .profileImage(signUpRequestDto.getProfileImage())
                .name(signUpRequestDto.getName())
                .studentId(signUpRequestDto.getStudentId())
                .email(signUpRequestDto.getEmail())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .build();
        return userRepository.save(user);
    }

    public UserEntity getUserByStudentId(String studentId) {
        return userRepository.findByStudentId(studentId).orElseThrow(() -> new UsernameNotFoundException("User not found with studentId: " + studentId));
    }
}
