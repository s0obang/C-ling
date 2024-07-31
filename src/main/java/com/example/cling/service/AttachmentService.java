package com.example.cling.service;

import com.example.cling.dto.NoticeDto;
import com.example.cling.dto.RecruitmentDto;
import com.example.cling.entity.Attachment;
import com.example.cling.entity.Notice;
import com.example.cling.entity.Recruitment;
import com.example.cling.repository.AttachmentRepository;
import com.example.cling.repository.NoticeRepository;
import com.example.cling.repository.RecruitmentRepository;
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
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final NoticeRepository noticeRepository;
    private final RecruitmentRepository recruitmentRepository;

    @Autowired
    public AttachmentService(AttachmentRepository attachmentRepository, NoticeRepository noticeRepository, RecruitmentRepository recruitmentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.noticeRepository = noticeRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    // yml 이미지 저장 경로 불러오기
    @Value("${spring.servlet.multipart.location}")
    private String baseDir;

    @Transactional
    public void uploadToNotice(List<MultipartFile> files, NoticeDto noticeDto) throws IOException {

        baseDir += File.separator + "notice";
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
            String imageUrl = "/attachments/notice/" + notice.getId() + "/" + imageName;
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
            Attachment image = Attachment.builder()
                    .originAttachmentName(originImageName)
                    .attachmentName(imageName)
                    .attachmentPath(imagePath)
                    .attachmentUrl(imageUrl)
                    .fileType("notice_image")
                    .notice(notice)
                    .build();
            attachmentRepository.save(image);

            // 이미지 리스트에 이미지 추가
            notice.addImage(image);
        }

        // Notice 객체의 상태를 데이터베이스에 반영
        noticeRepository.save(notice);
    }

    @Transactional
    public void uploadToRecruitment(List<MultipartFile> images, List<MultipartFile> files, RecruitmentDto recruitmentDto) throws IOException {

        baseDir += File.separator + "recruitment";
        //이미지가 없는 경우 저장 x
        if (images == null || images.isEmpty() || files == null || files.isEmpty()) {
            throw new IllegalArgumentException("이미지 또는 첨부파일이 없습니다.");
        }

        // 게시글 번호에 따라 디렉토리 생성
        String postDirName = baseDir + File.separator + recruitmentDto.getId();
        File postDir = new File(postDirName);
        if (!postDir.exists()) {
            if (postDir.mkdirs()) {
                System.out.println("Directory created: " + postDirName);
            } else {
                System.err.println("Failed to create directory: " + postDirName);
            }
        }

        Recruitment recruitment = recruitmentRepository.findById(recruitmentDto.getId())
                .orElseThrow(() -> new RuntimeException("Recruitment not found"));


        // 이미지 저장
        for (MultipartFile image : images) {

            String originImageName = image.getOriginalFilename();
            if (originImageName == null) {
                continue; // 저장 안하고 넘김
            }

            String imageName =  UUID.randomUUID().toString() + "_" + originImageName;
            String imagePath = postDirName + File.separator + imageName;
            String imageUrl = "/attachments/recruitment/" + recruitment.getId() + "/" + imageName;
            // 이미지 저장
            File dest = new File(imagePath);
            try {
                image.transferTo(dest);
                System.out.println("File saved: " + imagePath);
            } catch (IOException e) {
                System.err.println("Error saving file: " + imagePath);
                e.printStackTrace();
            }

            // DB에 정보 저장 및 recruitment와 연동
            Attachment savingImage = new Attachment(originImageName, imageName, imagePath, imageUrl, "recruitment_image", recruitment);
            attachmentRepository.save(savingImage);

            // 이미지 리스트에 이미지 추가
            recruitment.addImage(savingImage);
        }

        // 첨부파일 저장
        for (MultipartFile file : files) {

            String originFileName = file.getOriginalFilename();
            if (originFileName == null) {
                continue; // 저장 안하고 넘김
            }

            String fileName =  UUID.randomUUID().toString() + "_" + originFileName;
            String filePath = postDirName + File.separator + fileName;
            String fileUrl = "/attachments/recruitment/" + recruitment.getId() + "/" + fileName;
            // 첨부파일 저장
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
                System.out.println("File saved: " + filePath);
            } catch (IOException e) {
                System.err.println("Error saving file: " + filePath);
                e.printStackTrace();
            }

            // DB에 정보 저장 및 recruitment와 연동
            Attachment savingFile = new Attachment(originFileName, fileName, filePath, fileUrl, "recruitment_file", recruitment);
            attachmentRepository.save(savingFile);

            // 이미지 리스트에 이미지 추가
            recruitment.addFile(savingFile);
        }



        // recruitment 객체의 상태를 데이터베이스에 반영
        recruitmentRepository.save(recruitment);
    }
}
