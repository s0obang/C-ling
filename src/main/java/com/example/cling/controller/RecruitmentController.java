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

    // 작성한 공고 불러오기
    @GetMapping("/recruitment/all/{recruitingDepartment}")
    @ResponseStatus(HttpStatus.OK)
    public List<RecruitmentDto> getAllRecruitments(
            @PathVariable("recruitingDepartment") String recruitingDepartment
    ) { return recruitmentService.getAllRecruitments(recruitingDepartment); }

    // 공고 작성하기
    @PostMapping("/recruitment/write")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> writeRecruitment (
            @RequestParam("recruitingDepartment") String recruitingDepartment,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("step") String step,
            @RequestParam("startDate") String startDate,
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
                        .startDate(startDate)
                        .dueDate(dueDate)
                        .build();

        try {
            RecruitmentDto recruitmentDto = recruitmentService.write(recruitmentCreateDto);
            attachmentService.uploadToRecruitment(images, files, recruitmentDto);
            return ResponseEntity.ok("리크투팅 공고가 성공적으로 작성되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("리크루팅 공고 작성에 실패했습니다: " + e.getMessage());
        }

    }

    // 공고 상세보기
    @GetMapping("/recruitment/{recruitmentId}")
    public ResponseEntity<RecruitmentDto> getRecruitment(@PathVariable("recruitmentId") int recruitmentId) {
        Optional<RecruitmentDto> recruitmentDto = recruitmentService.getRecruitment(recruitmentId);
        return recruitmentDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 공고 삭제
    @DeleteMapping("/recruitment/{recruitmentId}")
    public ResponseEntity<Void> deleteRecruitment(
            @PathVariable("recruitmentId") String recruitmentId
    ) {
        recruitmentService.delete(Integer.parseInt(recruitmentId));
        return ResponseEntity.noContent().build();
    }

    //지원서 전송
    @PostMapping("/recruitment/apply/{recruitmentId}")
    public ResponseEntity<String> sendApplication (
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("application") List<MultipartFile> file,
            @PathVariable("recruitment_id") String recruitment_id
            ) {
        if (file == null)
            return ResponseEntity.badRequest().body("첨부파일이 없습니다.");

        String userId = userDetails.getUsername();

        Optional<UserEntity> user = userRepository.findById(userId);
        UserEntity student = user.orElseThrow(() -> new IllegalArgumentException("다시 로그인 하세요."));

        ApplicationCreateDto applicationCreateDto =
                ApplicationCreateDto.builder()
                        .recruitment_id(Integer.parseInt(recruitment_id))
                        .studentId(student.getStudentId())
                        .studentName(student.getName())
                        .application(file)
                        .build();

        try {
            ApplicationDto applicationDto = applicationService.send(applicationCreateDto);
            attachmentService.uploadToApplication(file, applicationDto, Integer.parseInt(recruitment_id));
            return ResponseEntity.ok("지원서가 성공적으로 전송되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("지원서 전송에 실패했습니다: " + e.getMessage());
        }

    }

    // 리크루팅 현황 정보 가져오기
    @GetMapping("/applications/{recruitingDepartment}/info")
    public RecruitmentInfoDto getRecruitmentInfo(
            @PathVariable("recruitingDepartment") String recruitingDepartment
    ) {
        //Dto 만들어서 서비스에서 id 값이 가장 큰 게시글(최신공고) 의 정보를 가져옴
        //Dto에는 title, 모집일정, 선발계획

        return recruitmentService.getRecruitmentInfo(recruitingDepartment);
    }

}
