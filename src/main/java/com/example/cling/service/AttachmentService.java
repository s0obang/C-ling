package com.example.cling.service;

import com.example.cling.dto.ApplicationDto;
import com.example.cling.dto.NoticeDto;
import com.example.cling.dto.RecruitmentDto;
import com.example.cling.entity.Application;
import com.example.cling.entity.Attachment;
import com.example.cling.entity.Notice;
import com.example.cling.entity.Recruitment;
import com.example.cling.repository.ApplicationRepository;
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
    private final ApplicationRepository applicationRepository;

    @Autowired
    public AttachmentService(AttachmentRepository attachmentRepository, NoticeRepository noticeRepository, RecruitmentRepository recruitmentRepository, ApplicationRepository applicationRepository) {
        this.attachmentRepository = attachmentRepository;
        this.noticeRepository = noticeRepository;
        this.recruitmentRepository = recruitmentRepository;
        this.applicationRepository = applicationRepository;
    }

    // yml 이미지 저장 경로 불러오기
    @Value("${spring.servlet.multipart.location}")
    private String baseDir;

    @Transactional
    public void uploadToNotice(List<MultipartFile> files, NoticeDto noticeDto) throws IOException {

        baseDir = "/home/ubuntu/uploads/Attachments" + File.separator + "notice";
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
                throw new IllegalArgumentException("디렉토리 생성에 실패했습니다.");
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
            String imageUrl = "/Attachments/notice/" + notice.getId() + "/" + imageName;
            // 이미지 저장
            File dest = new File(imagePath);
            try {
                file.transferTo(dest);
                System.out.println("File saved: " + imagePath);
            } catch (IOException e) {
                throw new IOException("Error saving file: " + imagePath, e);
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

            try {
                attachmentRepository.save(image);
            } catch (Exception e) {
                throw new RuntimeException("Error saving attachment to database", e);
            }
            // 이미지 리스트에 이미지 추가
            notice.addImage(image);
        }

        // Notice 객체의 상태를 데이터베이스에 반영
        try {
            noticeRepository.save(notice);
        } catch (Exception e) {
            throw new RuntimeException("Error updating notice in database", e);
        }
    }

    @Transactional
    public void uploadToRecruitment(List<MultipartFile> images, List<MultipartFile> files, RecruitmentDto recruitmentDto) throws IOException {

        baseDir = "/home/ubuntu/uploads/Attachments" + File.separator + "recruitment";
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
            String imageUrl = "/Attachments/recruitment/" + recruitment.getId() + "/" + imageName;
            // 이미지 저장
            File dest = new File(imagePath);
            try {
                image.transferTo(dest);
                System.out.println("File saved: " + imagePath);
            } catch (IOException e) {
                throw new IOException("Error saving file: " + imagePath, e);
            }

            // DB에 정보 저장 및 recruitment와 연동
            Attachment savingImage = Attachment.builder()
                    .originAttachmentName(originImageName)
                    .attachmentName(imageName)
                    .attachmentPath(imagePath)
                    .attachmentUrl(imageUrl)
                    .fileType("recruitment_image")
                    .recruitment(recruitment)
                    .build();
            try {
                attachmentRepository.save(savingImage);
            } catch (Exception e) {
                throw new RuntimeException("Error saving attachment to database", e);
            }
            // 이미지 리스트에 이미지 추가
            recruitment.addAttachment(savingImage);
        }

        // 첨부파일 저장
        for (MultipartFile file : files) {

            String originFileName = file.getOriginalFilename();
            if (originFileName == null) {
                continue; // 저장 안하고 넘김
            }

            String fileName =  UUID.randomUUID().toString() + "_" + originFileName;
            String filePath = postDirName + File.separator + fileName;
            String fileUrl = "/Attachments/recruitment/" + recruitment.getId() + "/" + fileName;
            // 첨부파일 저장
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
                System.out.println("File saved: " + filePath);
            } catch (IOException e) {
                throw new IOException("Error saving file: " + filePath, e);

            }

            // DB에 정보 저장 및 recruitment와 연동
            Attachment savingFile = Attachment.builder()
                    .originAttachmentName(originFileName)
                    .attachmentName(fileName)
                    .attachmentPath(filePath)
                    .attachmentUrl(fileUrl)
                    .fileType("recruitment_file")
                    .recruitment(recruitment)
                    .build();
            try {
                attachmentRepository.save(savingFile);
            } catch (Exception e) {
                throw new RuntimeException("Error saving attachment to database", e);
            }
            // 이미지 리스트에 이미지 추가
            recruitment.addAttachment(savingFile);
        }

        try {
            // recruitment 객체의 상태를 데이터베이스에 반영
            recruitmentRepository.save(recruitment);
        } catch (Exception e) {
            throw new RuntimeException("Error updating notice in database", e);
        }

    }

    public void uploadToApplication(List<MultipartFile> file, ApplicationDto applicationDto, int recruitment_id) throws IOException {

        baseDir = "/home/ubuntu/uploads/Attachments" + File.separator + "recruitment" + File.separator + recruitment_id;
        //이미지가 없는  경우 저장 x
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("지원서가 없습니다.");
        }

        // 공고 id 폴더 밑에 application 폴더 생성
        String postDirName = baseDir + File.separator + "application" + File.separator + applicationDto.getId();
        File postDir = new File(postDirName);
        if (!postDir.exists()) {
            if (postDir.mkdirs()) {
                System.out.println("Directory created: " + postDirName);
            } else {
                System.err.println("Failed to create directory: " + postDirName);
            }
        }

        Application application = applicationRepository.findById(applicationDto.getId())
                .orElseThrow(() -> new RuntimeException("Application not found"));

        // 지원서 저장
        for (MultipartFile attachment : file) {

            String originFileName = attachment.getOriginalFilename();
            if (originFileName == null) {
                continue; // 저장 안하고 넘김
            }

            String fileName =  UUID.randomUUID().toString() + "_" + originFileName;
            String filePath = postDirName + File.separator + fileName;
            String fileUrl = "/Attachments/recruitment/" + recruitment_id + "/application/" + application.getId() + "/" + fileName;
            // 지원서 저장
            File dest = new File(filePath);
            try {
                attachment.transferTo(dest);
                System.out.println("File saved: " + filePath);
            } catch (IOException e) {
                throw new IOException("Error saving file: " + filePath, e);
            }

            // DB에 정보 저장 및 application과 연동
            Attachment syncAttachment = Attachment.builder()
                    .originAttachmentName(originFileName)
                    .attachmentName(fileName)
                    .attachmentPath(filePath)
                    .attachmentUrl(fileUrl)
                    .fileType("application_file")
                    .application(application)
                    .build();
            try {
                attachmentRepository.save(syncAttachment);
            } catch (Exception e) {
                throw new RuntimeException("Error saving attachment to database", e);
            }
            // appliction에 추가
            application.addFile(syncAttachment);
        }

        // application 객체의 상태를 데이터베이스에 반영
        try {
            applicationRepository.save(application);
        } catch (Exception e) {
            throw new RuntimeException("Error updating application in database", e);
        }
        applicationRepository.save(application);

    }

    public Attachment getAttachmentById(int id) {
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Attachment not found with id " + id));
    }

    // 인스턴스에서 파일 삭제
    public void deleteFileFromFileSystem(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully: " + filePath);
            } else {
                System.err.println("Failed to delete file: " + filePath);
            }
        } else {
            System.err.println("File not found: " + filePath);
        }
    }

    // DB에서 파일 정보 삭제
    @Transactional
    public void deleteFileFromDatabase(int attachmentId) {
        attachmentRepository.deleteById(attachmentId);
    }

    // 인스턴스 및 DB에서 파일 삭제
    @Transactional
    public void deleteFile(Attachment attachment) {
        deleteFileFromFileSystem(attachment.getAttachmentPath());
        deleteFileFromDatabase(attachment.getId());
    }

}
