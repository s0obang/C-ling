package com.example.cling.repository;

import com.example.cling.entity.Request;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    @Query("SELECT rq.id FROM Request rq WHERE rq.requester.studentId = :requesterId AND rq.requestee.studentId = :requesteeId")
    Optional<Integer> findIdByRequesterAndRequestee(@Param("requesterId") String requesterId, @Param("requesteeId") String requesteeId);
    List<Request> findByRequestee_StudentId(String requesteeId);
}
