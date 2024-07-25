package com.example.cling.service;

import com.example.cling.dto.SignUpRequestDto;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

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

    public boolean loginUser(String studentId, String enteredPassword) {
        UserEntity userEntity = userRepository.findByStudentId(studentId)
                .orElse(null);

        if (userEntity == null) {
            return false;
        }

        boolean passwordMatches = passwordEncoder.matches(enteredPassword, userEntity.getPassword());

        if (passwordMatches) {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession();
            session.setAttribute("userId", studentId);

            return true;
        } else {
            return false;
        }
    }

    public UserEntity getUserByStudentId(String studentId) {
        return userRepository.findByStudentId(studentId).orElseThrow(() -> new UsernameNotFoundException("User not found with studentId: " + studentId));
    }
}
