package com.example.cling.repository;

import com.example.cling.entity.UserEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByStudentId(String studentId);

    // 이메일로 사용자 조회
    Optional<UserEntity> findByEmail(String email);

    @Query(value = "SELECT * FROM user u WHERE u.major = :major AND u.student_id != :studentId AND u.student_id NOT IN (" +
            "SELECT m.student_id_1 FROM matching m WHERE m.student_id_2 = :studentId " +
            "UNION " +
            "SELECT m.student_id_2 FROM matching m WHERE m.student_id_1 = :studentId) " +
            "ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<UserEntity> findSameMajorUsers(@Param("major") String major, @Param("studentId") String studentId);

    @Query(value = "SELECT * FROM user u WHERE u.major != :major AND u.student_id != :studentId AND u.student_id NOT IN (" +
            "SELECT m.student_id_1 FROM matching m WHERE m.student_id_2 = :studentId " +
            "UNION " +
            "SELECT m.student_id_2 FROM matching m WHERE m.student_id_1 = :studentId) " +
            "ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<UserEntity> findOtherMajorUsers(@Param("major") String major, @Param("studentId") String studentId);
}