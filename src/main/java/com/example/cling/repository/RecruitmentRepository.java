package com.example.cling.repository;

import com.example.cling.entity.Recruitment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
    List<Recruitment> findByRecruitingDepartment(String recruitingDepartment);

    Recruitment findFirstByRecruitingDepartmentOrderByIdDesc(String recruitingDepartment);

}
