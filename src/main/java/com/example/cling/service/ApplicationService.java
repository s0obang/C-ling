package com.example.cling.service;

import com.example.cling.dto.ApplicationCreateDto;
import com.example.cling.dto.ApplicationDto;
import com.example.cling.entity.Application;
import com.example.cling.entity.Recruitment;
import com.example.cling.entity.Attachment;
import com.example.cling.entity.Recruitment;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.ApplicationRepository;
import com.example.cling.repository.RecruitmentRepository;
import com.example.cling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final MailSendService mailSendService;
    private final UserRepository userRepository;
    private final RecruitmentRepository recruitmentRepository;



    public ApplicationDto send(ApplicationCreateDto applicationCreateDto) {
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
        recruitment.addApplication(application);
        Application savedApplication = applicationRepository.save(application);
        return ApplicationDto.toDto(savedApplication);


    }

    public List<ApplicationDto> getApplications(String recruitingDepartment) {
        Recruitment recruitment = recruitmentRepository.findFirstByRecruitingDepartmentOrderByIdDesc(recruitingDepartment);

        if (recruitment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "부서 이름을 다시 확인하세요." + recruitingDepartment);
        }

        List<Application> applications = recruitment.getApplications();
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        applications.forEach(s -> applicationDtos.add(ApplicationDto.toDto(s)));
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
                    String subject = "[크링]에서 알람이 도착했어요!";
                    String content = user.getName() + "님, 지원하신 크루에서 합격 여부를 등록 완료했어요!" + // html 형식으로 작성
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

        // Recruitment의 step과 비교하여 모집 완료 여부 확인
        Optional<Recruitment> recruitmentOptional = recruitmentRepository.findById(recruitingId);
        if (recruitmentOptional.isPresent()) {
            Recruitment recruitment = recruitmentOptional.get();

            if (Integer.toString(step).equals(recruitment.getStep())) {
                try {
                    recruitment.setOnStep("0");
                    recruitmentRepository.save(recruitment);
                } catch (Exception e) {
                    System.err.println("Failed to update recruitment step : " + e.getMessage());
                }
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

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream))) {
            for (Application app : applications) {
                writer.printf("학번: %s, 이름: %s%n", app.getStudentId(), app.getStudentName());
            }
        }

        return new ByteArrayResource(outputStream.toByteArray());
    }


}
