package com.example.cling.service;

import com.example.cling.dto.NoticeDto;
import com.example.cling.entity.Image;
import com.example.cling.entity.Notice;
import com.example.cling.repository.ImageRepository;
import com.example.cling.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final NoticeRepository noticeRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, NoticeRepository noticeRepository) {
        this.imageRepository = imageRepository;
        this.noticeRepository = noticeRepository;
    }

    // yml 이미지 저장 경로 불러오기
    @Value("${spring.servlet.multipart.location}")
    private String baseDir;

    @Transactional
    public void upload(List<MultipartFile> files, NoticeDto noticeDto) throws IOException {

        //이미지가 없는 경우 저장 x
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("이미지 파일이 없습니다.");
        }

        // 게시글 번호에 따라 디렉토리 생성
        String postDirName = baseDir + File.separator + noticeDto.getId();
        File postDir = new File(postDirName);
        if (!postDir.exists()) {
            if (postDir.mkdirs()) {
                System.out.println("Directory created: " + postDirName);
            } else {
                System.err.println("Failed to create directory: " + postDirName);
            }
        }

        Notice notice = noticeRepository.findById(noticeDto.getId())
                .orElseThrow(() -> new RuntimeException("Notice not found"));

        // 이미지 저장
        for (MultipartFile file : files) {

            String originImageName = file.getOriginalFilename();
            if (originImageName == null) {
                continue; // 저장 안하고 넘김
            }

            String imageName =  UUID.randomUUID().toString() + "_" + originImageName;
            String imagePath = postDirName + File.separator + imageName;

            // 이미지 저장
            File dest = new File(imagePath);
            try {
                file.transferTo(dest);
                System.out.println("File saved: " + imagePath);
            } catch (IOException e) {
                System.err.println("Error saving file: " + imagePath);
                e.printStackTrace();
            }

            // DB에 정보 저장 및 Notice와 연동
            Image image = Image.builder()
                    .originImageName(originImageName)
                    .imageName(imageName)
                    .imagePath(imagePath)
                    .notice(notice)
                    .build();
            imageRepository.save(image);

            // 이미지 리스트에 이미지 추가
            notice.addImage(image);
        }

        // Notice 객체의 상태를 데이터베이스에 반영
        noticeRepository.save(notice);
    }
}
