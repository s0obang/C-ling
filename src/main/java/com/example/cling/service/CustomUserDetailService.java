package com.example.cling.service;

import com.example.cling.entity.UserEntity;
import com.example.cling.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with studentId: " + studentId));
        return new org.springframework.security.core.userdetails.User(user.getStudentId(), user.getPassword(), new ArrayList<>());
    }
}
