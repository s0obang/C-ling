package com.example.cling.repository;

import com.example.cling.entity.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchingRepository extends JpaRepository<Matching, Integer> {
    List<Matching> findByStudent1_StudentIdOrStudent2_StudentId(String studentId1, String studentId2);

}
