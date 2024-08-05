package com.example.cling.repository;

import com.example.cling.entity.Application;
import com.example.cling.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Application findByStudentId(String studentId);
    List<Application> findByRecruitingDepartment(String recruitingDepartment);

    List<Application> findByRecruitment(Recruitment recruitment);
}
