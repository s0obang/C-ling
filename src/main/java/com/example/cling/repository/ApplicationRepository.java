package com.example.cling.repository;

import com.example.cling.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Application findByStudentId(String studentId);
}
