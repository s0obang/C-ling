package com.example.cling.controller;

import com.example.cling.dto.NoticeCreateDto;
import com.example.cling.dto.NoticeDto;
import com.example.cling.service.NoticeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NoticeController {
    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    @ResponseStatus(HttpStatus.OK)
    public List<NoticeDto> getAllNotices() {
        return noticeService.getAllNotices();
    }

    @PostMapping("/notice/write")
    @ResponseStatus(HttpStatus.CREATED)
    public void writeNotice(@RequestBody @Valid NoticeCreateDto noticeCreateDto) {
        noticeService.write(noticeCreateDto);
    }

    @GetMapping("/notice/{noticeId}")
    public ResponseEntity<NoticeDto> getNotice(@PathVariable("noticeId") int noticeId) {
        Optional<NoticeDto> noticeDto = noticeService.getNotice(noticeId);
        return noticeDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/notice/{noticeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNotice(@PathVariable("noticeId") int noticeId) {
        noticeService.delete(noticeId);
    }
}
