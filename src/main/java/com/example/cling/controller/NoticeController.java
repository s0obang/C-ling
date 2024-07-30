package com.example.cling.controller;

import com.example.cling.dto.NoticeCreateDto;
import com.example.cling.dto.NoticeDto;
import com.example.cling.entity.Notice;
import com.example.cling.service.ImageService;
import com.example.cling.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class NoticeController {
    private final NoticeService noticeService;
    private final ImageService imageService;

    @Autowired
    public NoticeController(NoticeService noticeService, ImageService imageService) {
        this.noticeService = noticeService;
        this.imageService = imageService;
    }


    @GetMapping("/notice")
    @ResponseStatus(HttpStatus.OK)
    public List<NoticeDto> getAllNotices() {
        return noticeService.getAllNotices();
    }

    @PostMapping("/notice/write")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> writeNotice( //vo로 받을지 파라미터로 받을지
                                               @AuthenticationPrincipal UserDetails userDetails,
                                               @RequestParam("title") String title,
                                               @RequestParam("content") String content,
                                               @RequestParam("images") List<MultipartFile> files
    ) throws Exception {

        //이미지가 없는 경우 저장 x
        if (files == null || files.isEmpty()) {
            return ResponseEntity.badRequest().body("이미지를 하나 이상 선택해야 합니다.");
        }

        String authenticatedUserId = userDetails.getUsername();

        NoticeCreateDto noticeCreateDto =
                NoticeCreateDto.builder()
                        .userId(authenticatedUserId)
                        .title(title)
                        .content(content)
                        .build();
        NoticeDto noticeDto = noticeService.write(noticeCreateDto);
        imageService.upload(files, noticeDto);

        return ResponseEntity.ok("게시글이 성공적으로 작성되었습니다.");
    }

    @GetMapping("/notice/{noticeId}")
    public ResponseEntity<NoticeDto> getNotice(@PathVariable("noticeId") int noticeId) {
        Optional<NoticeDto> noticeDto = noticeService.getNotice(noticeId);
        return noticeDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/notice/{noticeId}")
    public ResponseEntity<Void> deleteNotice(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("noticeId") int noticeId
    ) {

        String authenticatedUserId = userDetails.getUsername();

        // 삭제 권환 확인
        Notice notice = noticeService.getNoticeById(noticeId);
        if (!notice.getUserId().equals(authenticatedUserId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 권한 없음 응답
        }
        // 게시물 삭제
        noticeService.delete(noticeId);
        return ResponseEntity.noContent().build();
    }
}
