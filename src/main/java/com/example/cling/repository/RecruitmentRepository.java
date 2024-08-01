package com.example.cling.repository;

import com.example.cling.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
    List<Recruitment> findByRecruitingDepartment(String recruitingDepartment);
}
