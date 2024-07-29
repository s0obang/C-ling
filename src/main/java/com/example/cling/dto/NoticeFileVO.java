package com.example.cling.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class NoticeFileVO {
    private String title;
    private String content;
    private List<MultipartFile> images;
}
