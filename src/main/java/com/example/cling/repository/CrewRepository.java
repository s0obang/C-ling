package com.example.cling.repository;

import com.example.cling.entity.Crew;
import com.example.cling.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CrewRepository extends JpaRepository<Crew, Long> {
    List<Crew> findByUser(UserEntity user);

    Optional<Crew> findByUserAndCrewName(UserEntity user, String crewName);
}
