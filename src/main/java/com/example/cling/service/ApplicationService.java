package com.example.cling.service;

import com.example.cling.dto.ApplicationCreateDto;
import com.example.cling.dto.ApplicationDto;
import com.example.cling.entity.*;
import com.example.cling.entity.Recruitment;
import com.example.cling.repository.ApplicationRepository;
import com.example.cling.repository.RecruitmentRepository;
import com.example.cling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final MailSendService mailSendService;
    private final UserRepository userRepository;
    private final RecruitmentRepository recruitmentRepository;



    public ApplicationDto send(ApplicationCreateDto applicationCreateDto) throws IOException {
        Application application = new Application();

        // 공고 찾기
        Optional<Recruitment> getRecruitment = recruitmentRepository.findById(Integer.valueOf(applicationCreateDto.getRecruitment_id()));
        Recruitment recruitment;
        if (getRecruitment.isPresent())
            recruitment =  getRecruitment.get();
        else
            throw new IllegalArgumentException("리크루팅 공고가 존재하지 않습니다.");
        application.setRecruitment(recruitment);
        application.setRecruitingDepartment(recruitment.getRecruitingDepartment());
        application.setStudentId(applicationCreateDto.getStudentId());
        application.setStudentName(applicationCreateDto.getStudentName());
        application.setView(true);
        recruitment.addApplication(application);
        Application savedApplication = applicationRepository.save(application);
        return ApplicationDto.toDto(savedApplication);


    }

    public List<ApplicationDto> getApplications(String recruitingDepartment) {
        Recruitment recruitment = recruitmentRepository.findFirstByRecruitingDepartmentOrderByIdDesc(recruitingDepartment);

        if (recruitment == null) {
            return Collections.emptyList();
        }

        List<Application> applications = recruitment.getApplications();
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        applications.forEach(s -> {
            try {
                applicationDtos.add(ApplicationDto.toDto(s));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return applicationDtos;
    }


    public void updateResults(int step, Map<String, Boolean> results, String recruitingDepartment, int recruitingId) {
        for (Map.Entry<String, Boolean> entry : results.entrySet()) {
            String studentId = entry.getKey();
            Boolean result = entry.getValue();

            // 학번과 모집 부서로 application 객체 찾기
            Optional<Application> applicationOptional = applicationRepository.findByStudentIdAndRecruitingDepartment(studentId, recruitingDepartment);
            if (applicationOptional.isPresent()) {
                Application application = applicationOptional.get();
                application.updateResult(step, result);
                applicationRepository.save(application);

                // 사용자 정보 조회
                Optional<UserEntity> userOptional = userRepository.findByStudentId(studentId);
                if (userOptional.isPresent()) {
                    UserEntity user = userOptional.get();
                    String subject = "[크링]" + application.getRecruitingDepartment() + " 크루에서 알람이 도착했어요!";
                    String content = user.getName() + "님, 지원하신 " + application.getRecruitingDepartment() + "크루에서 합격 여부를 등록 완료했어요!" + // html 형식으로 작성
                            "<br><br>" +
                            "지금 CREW > 현재 지원 현황에서 합격 여부를 확인할 수 있습니다";
                    try {
                        mailSendService.mailSend("quffl2002@gmail.com", user.getEmail(), subject, content);
                    } catch (Exception e) {
                        // 이메일 전송 실패 시 로깅
                        System.err.println("Failed to send email to " + user.getEmail() + ": " + e.getMessage());
                    }
                }
            } else {
                throw new IllegalArgumentException("Application not found for student ID: " + studentId + " and recruiting department: " + recruitingDepartment);
            }
        }
        updateOnStep(step, recruitingId);

    }

    public void updateOnStep(int step, int recruitingId) {
        String newOnStep = (step == 1) ? "2" : (step == 2) ? "3" : null;

        if (newOnStep != null) {
            try {
                recruitmentRepository.updateOnStepNative(newOnStep, recruitingId);
            } catch (Exception e) {
                System.err.println("Failed to update recruitment step: " + e.getMessage());
            }
        }
    }


    public Resource generateStudentList(String recruitingDepartment, int step) throws IOException {
        List<Application> applications;

        if (step == 1) {
            applications = applicationRepository.findByRecruitingDepartmentAndFirstResult(recruitingDepartment, true);
        } else if (step == 2) {
            applications = applicationRepository.findByRecruitingDepartmentAndSecondResult(recruitingDepartment, true);
        } else {
            throw new IllegalArgumentException("Invalid step value: " + step);
        }

        // 데이터 존재 여부 확인
        if (applications.isEmpty()) {
            throw new IllegalStateException("No applications found for the given parameters.");
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream))) {
            for (Application app : applications) {
                writer.printf("학번: %s, 이름: %s%n", app.getStudentId(), app.getStudentName());
            }
            // Writer를 수동으로 flush
            writer.flush();
        }

        return new ByteArrayResource(outputStream.toByteArray());
    }


    public List<ApplicationDto> getMyApplications(String userId) {
        List<Application> applications = applicationRepository.findByStudentId(userId);
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        applications.forEach(s -> {
            try {
                applicationDtos.add(ApplicationDto.toDto(s));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return  applicationDtos;
    }

    public boolean checkApplication(String userId, int recruitmentId) {
        return applicationRepository.findByStudentIdAndRecruitmentId(userId, recruitmentId).isPresent();
    }

    public void setDisplayNone(int applicationId) {
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isPresent()) {
            Application application = applicationOptional.get();
            application.setView(false);
            applicationRepository.save(application);
        } else throw new RuntimeException("지원서를 찾을 수 없습니다.");
    }
}
