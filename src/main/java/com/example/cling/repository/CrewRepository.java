package com.example.cling.repository;

import com.example.cling.entity.Crew;
import com.example.cling.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CrewRepository extends JpaRepository<Crew, Long> {
    List<Crew> findByUser(UserEntity user);
}