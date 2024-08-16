package com.example.cling.repository;

import com.example.cling.entity.Recruitment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
    List<Recruitment> findByRecruitingDepartment(String recruitingDepartment);

    Recruitment findFirstByRecruitingDepartmentOrderByIdDesc(String recruitingDepartment);

    List<Recruitment> findAllByOrderByIdDesc(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE recruitment SET on_step = :onStep WHERE id = :recruitingId", nativeQuery = true)
    void updateOnStepNative(@Param("onStep") String onStep, @Param("recruitingId") int recruitingId);

}
