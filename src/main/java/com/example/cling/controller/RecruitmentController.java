package com.example.cling.controller;

import com.example.cling.dto.*;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.UserRepository;
import com.example.cling.service.ApplicationService;
import com.example.cling.service.AttachmentService;
import com.example.cling.service.RecruitmentService;
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
public class RecruitmentController {

    private final RecruitmentService recruitmentService;
    private final ApplicationService applicationService;
    private final AttachmentService attachmentService;

    private final UserRepository userRepository;

    @Autowired
    public RecruitmentController(RecruitmentService recruitmentService, ApplicationService applicationService, AttachmentService attachmentService, UserRepository userRepository) {
        this.recruitmentService = recruitmentService;
        this.applicationService = applicationService;
        this.attachmentService = attachmentService;
        this.userRepository = userRepository;
    }

    @GetMapping("/recruitment/{recruitingDepartment}")
    @ResponseStatus(HttpStatus.OK)
    public List<RecruitmentDto> getAllRecruitments(
            @PathVariable("recruitingDepartment") String recruitingDepartment
    ) { return recruitmentService.getAllRecruitments(recruitingDepartment); }

    @PostMapping("/recruitment/write")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> writeRecruitment (
            @RequestParam("recruitingDepartment") String recruitingDepartment,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("step") String step,
            @RequestParam("dueDate") String dueDate,
            @RequestParam("images") List<MultipartFile> images,
            @RequestParam("file") List<MultipartFile> files
    ) throws Exception {

        //이미지 또는 첨부 파일이 없는 경우 저장 x
        if (images == null || images.isEmpty() || files == null || files.isEmpty()) {
            return ResponseEntity.badRequest().body("이미지와 첨부파일을 각각 하나 이상 선택해야 합니다.");
        }

        RecruitmentCreateDto recruitmentCreateDto =
                RecruitmentCreateDto.builder()
                        .RecruitingDepartment(recruitingDepartment)
                        .title(title)
                        .content(content)
                        .step(step)
                        .dueDate(dueDate)
                        .build();

        RecruitmentDto recruitmentDto = recruitmentService.write(recruitmentCreateDto);
        attachmentService.uploadToRecruitment(images, files, recruitmentDto);
        return ResponseEntity.ok("게시글이 성공적으로 작성되었습니다.");
    }

    @GetMapping("/recruitment/{recruitmentId}")
    public ResponseEntity<RecruitmentDto> getRecruitment(@PathVariable("recruitmentId") int recruitmentId) {
        Optional<RecruitmentDto> recruitmentDto = recruitmentService.getRecruitment(recruitmentId);
        return recruitmentDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/recruitment/{recruitmentId}")
    public ResponseEntity<Void> deleteRecruitment(
            @PathVariable("recruitmentId") int recruitmentId
    ) {
        recruitmentService.delete(recruitmentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/recruitment/apply/{recruitingDepartment")
    public ResponseEntity<String> sendApplication (
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("application") List<MultipartFile> file,
            @PathVariable("recruitingDepartment") String recruitingDepartment
            ) {
        if (file == null)
            return ResponseEntity.badRequest().body("첨부파일이 없습니다.");

        String userId = userDetails.getUsername();
        Optional<UserEntity> user = userRepository.findById(userId);
        UserEntity student;
        if (user != null)
            student = user.get();
        else throw new IllegalArgumentException("다시 로그인 하세요.");

        ApplicationCreateDto applicationCreateDto =
                ApplicationCreateDto.builder()
                        .recruitingDepartment(recruitingDepartment)
                        .studentId(userId)
                        .studentName(student.getName())
                        .build();

        ApplicationDto applicationDto = applicationService.send(applicationCreateDto);

        attachmentService.uploadToApplication(file, applicationDto);

        return ResponseEntity.ok("지원서가 성공적으로 전송되었습니다.");
    }
}
