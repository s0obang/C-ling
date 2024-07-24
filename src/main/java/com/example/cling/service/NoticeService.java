package com.example.cling.service;

import com.example.cling.dto.NoticeCreateDto;
import com.example.cling.dto.NoticeDto;
import com.example.cling.entity.Notice;
import com.example.cling.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

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

    //게시물 삭제
    @Transactional
    public void delete(int id) {

        //게시물이 없는 경우 예외 처리
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("삭제할 게시물이 없습니다.");
        });
        //게시물이 있는 경우 삭제
        noticeRepository.deleteById(id);
    }
}
