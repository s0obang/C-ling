package com.example.cling.service;

import com.example.cling.dto.ApplicationCreateDto;
import com.example.cling.dto.ApplicationDto;
import com.example.cling.entity.Application;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.ApplicationRepository;
import com.example.cling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final MailSendService mailSendService;
    private final UserRepository userRepository;



    public ApplicationDto send(ApplicationCreateDto applicationCreateDto) {
        Application application = new Application();
        application.setRecruitingDepartment(applicationCreateDto.getRecruitingDepartment());
        application.setStudentId(application.getStudentId());
        application.setStudentName(application.getStudentName());
        Application savedApplication = applicationRepository.save(application);
        return ApplicationDto.toDto(savedApplication);


    }


    public void updateResults(int step, Map<String, Boolean> results) {
        for (Map.Entry<String, Boolean> entry : results.entrySet()) {
            String studentId = entry.getKey();
            Boolean result = entry.getValue();

            //학번으로 application객체 찾기
            Application application = applicationRepository.findByStudentId(studentId);
            if (application != null) {
                application.updateResult(step, result);
                applicationRepository.save(application);

                //사용자 정보 조회임
                Optional<UserEntity> userOptional = userRepository.findByStudentId(studentId);
                if (userOptional.isPresent()) {
                    UserEntity user = userOptional.get();
                    String subject = "지원 결과 통보";
                    String resultText = result ? "합격" : "불합격";
                    String content = String.format("%s님, 지원하신 부서에 대한 결과를 알려드립니다. 결과: %s", user.getName(), resultText);
                    try {
                        mailSendService.mailSend("dionisos198@naver.com", user.getEmail(), subject, content);
                    } catch (Exception e) {
                        // 이메일 전송 실패 시 로깅
                        System.err.println("Failed to send email to " + user.getEmail() + ": " + e.getMessage());
                    }
                }
            } else {
                throw new IllegalArgumentException("Application not found for student ID: " + studentId);
            }
        }
    }


}
