package com.example.cling.service;

import com.example.cling.dto.ProfileImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfileImageService {
    void upload(MultipartFile file, String studentId);
    ProfileImageResponseDto findImage(String studentId);
    byte[] getImageBytes(String fileUrl) throws IOException;
}
