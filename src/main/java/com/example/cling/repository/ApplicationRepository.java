package com.example.cling.repository;

import com.example.cling.entity.Application;
import com.example.cling.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Application findByStudentId(String studentId);
    List<Application> findByRecruitingDepartment(String recruitingDepartment);
    List<Application> findByRecruitingDepartmentAndFirstResult(String recruitingDepartment, boolean firstResult);
    List<Application> findByRecruitingDepartmentAndSecondResult(String recruitingDepartment, boolean secondResult);

    List<Application> findByRecruitment(Recruitment recruitment);

    Optional<Application> findByStudentIdAndRecruitingDepartment(String studentId, String recruitingDepartment);
}
