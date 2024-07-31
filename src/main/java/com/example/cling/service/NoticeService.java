package com.example.cling.service;

import com.example.cling.dto.NoticeCreateDto;
import com.example.cling.dto.NoticeDto;
import com.example.cling.entity.Attachment;
import com.example.cling.entity.Notice;
import com.example.cling.repository.AttachmentRepository;
import com.example.cling.repository.NoticeRepository;
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
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final AttachmentRepository attachmentRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository, AttachmentRepository attachmentRepository) {
        this.noticeRepository = noticeRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @Value("${spring.servlet.multipart.location}")
    private String baseDir;

    // 전체 게시물 조회
    @Transactional
    public List<NoticeDto> getAllNotices() {
        List<Notice> notices = noticeRepository.findAll();
        List<NoticeDto> noticeDtos = new ArrayList<>();
        notices.forEach(s -> noticeDtos.add(NoticeDto.toDto(s)));
        return noticeDtos;
    }

    // 게시물 작성
    @Transactional
    public NoticeDto write(NoticeCreateDto noticeCreateDto) {
        Notice notice = new Notice();
        notice.setUserId(noticeCreateDto.getUserId());
        notice.setTitle(noticeCreateDto.getTitle());
        notice.setContent(noticeCreateDto.getContent());
        Notice savedNotice = noticeRepository.save(notice);
        return NoticeDto.toDto(savedNotice);
    }

    // 게시물 조회
    public Optional<NoticeDto> getNotice(int id) {
        return noticeRepository.findById(id)
                .map(NoticeDto::toDto);
    }

    public Notice getNoticeById(int id) {
        return noticeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Notice not found"));
    }

    //게시물 삭제
    @Transactional
    public void delete(int id) {

        //게시물이 없는 경우 예외 처리
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("삭제할 게시물이 없습니다.");
        });

        for (Attachment image : notice.getImages()) {
            File file = new File(image.getAttachmentPath());
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("File deleted: " + file.getPath());
                } else {
                    System.err.println("Failed to delete file: " + file.getPath());
                }
            }
        }

        attachmentRepository.deleteAll(notice.getImages());

        baseDir += File.separator + "notice";
        // 게시물 아이디에 해당하는 폴더 삭제
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

        //게시물이 있는 경우 삭제
        noticeRepository.deleteById(id);

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
