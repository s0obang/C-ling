package com.example.cling.service;

import com.example.cling.entity.UserEntity;
import com.example.cling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with studentId: " + studentId));
        return User.builder()
                .username(userEntity.getStudentId())
                .password(userEntity.getPassword())
                .roles("USER") // 역할은 우리 필요에 따라서 설정하면 된다는데 슈퍼유저 빼고는 다 걍 유저니까 유저 멕여놓음
                .build();
    }
}
