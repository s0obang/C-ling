package com.example.cling.service;

import com.example.cling.dto.ApplicationCreateDto;
import com.example.cling.dto.ApplicationDto;
import com.example.cling.entity.Application;
import com.example.cling.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public ApplicationDto send(ApplicationCreateDto applicationCreateDto) {
        Application application = new Application();
        application.setRecruitingDepartment(applicationCreateDto.getRecruitingDepartment());
        application.setStudentId(application.getStudentId());
        application.setStudentName(application.getStudentName());
        Application savedApplication = applicationRepository.save(application);
        return ApplicationDto.toDto(savedApplication);


    }


}
