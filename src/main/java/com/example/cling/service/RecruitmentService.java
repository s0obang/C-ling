package com.example.cling.service;

import com.example.cling.dto.RecruitmentCreateDto;
import com.example.cling.dto.RecruitmentDto;
import com.example.cling.entity.Attachment;
import com.example.cling.entity.Recruitment;
import com.example.cling.repository.AttachmentRepository;
import com.example.cling.repository.RecruitmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final AttachmentRepository attachmentRepository;

    @Autowired
    public RecruitmentService(RecruitmentRepository recruitmentRepository, AttachmentRepository attachmentRepository) {
        this.recruitmentRepository = recruitmentRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @Value("${spring.servlet.multipart.location}")
    private String baseDir;

    // 전체 게시물 조회
    @Transactional
    public List<RecruitmentDto> getAllRecruitments() {
        List<Recruitment> recruitments = recruitmentRepository.findAll();
        List<RecruitmentDto> recruitmentDtos = new ArrayList<>();
        recruitments.forEach(s -> recruitmentDtos.add(RecruitmentDto.toDto(s)));
        return recruitmentDtos;
    }

    // 게시물 작성
    @Transactional
    public RecruitmentDto write(RecruitmentCreateDto recruitmentCreateDto) {
        Recruitment recruitment = new Recruitment();
        recruitment.setRecruitingDepartment(recruitmentCreateDto.getRecruitingDepartment());
        recruitment.setTitle(recruitmentCreateDto.getTitle());
        recruitment.setContent(recruitmentCreateDto.getContent());
        Recruitment savedRecruitment = recruitmentRepository.save(recruitment);
        return RecruitmentDto.toDto(savedRecruitment);
    }

    // 게시물 조회
    public Optional<RecruitmentDto> getNotice(int id) {
        return recruitmentRepository.findById(id)
                .map(RecruitmentDto::toDto);
    }

    public Recruitment getRecruitmentById(int id) {
        return recruitmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Notice not found"));
    }

    //게시물 삭제
    @Transactional
    public void delete(int id) {

        //게시물이 없는 경우 예외 처리
        Recruitment recruitment = recruitmentRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("삭제할 게시물이 없습니다.");
        });

        for (Attachment image : recruitment.getImages()) {
            File file = new File(image.getAttachmentPath());
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("Image deleted: " + file.getPath());
                } else {
                    System.err.println("Failed to delete image: " + file.getPath());
                }
            }
        }
        for (Attachment attachment : recruitment.getFiles()) {
            File file = new File(attachment.getAttachmentPath());
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("Attachment deleted: " + file.getPath());
                } else {
                    System.err.println("Failed to delete attachment: " + file.getPath());
                }
            }
        }

        attachmentRepository.deleteAll(recruitment.getImages());
        attachmentRepository.deleteAll(recruitment.getFiles());

        // 게시물 아이디에 해당하는 폴더 삭제
        baseDir += File.separator + "recruitment";
        String postDirName = baseDir + File.separator + id;
        File postDir = new File(postDirName);
        if (postDir.exists() && postDir.isDirectory()) {
            try {
                deleteDirectoryRecursively(postDir);
            } catch (IOException e) {
                System.err.println("Error deleting directory: " + postDirName);
                e.printStackTrace();
            }
        }

        //게시물 삭제
        recruitmentRepository.deleteById(id);

    }

    private void deleteDirectoryRecursively(File directory) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectoryRecursively(file);
                } else {
                    if (!file.delete()) {
                        throw new IOException("Failed to delete file: " + file.getPath());
                    }
                }
            }
        }
        if (!directory.delete()) {
            throw new IOException("Failed to delete directory: " + directory.getPath());
        }
    }
}
