package com.example.cling.service;

import com.example.cling.dto.CrewRequestDto;
import com.example.cling.entity.Crew;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.CrewRepository;
import com.example.cling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrewService {

    private final CrewRepository crewRepository;
    private final UserRepository userRepository;

    public void addCrew(String studentId, CrewRequestDto crewRequestDto) {
        UserEntity user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Crew crew = Crew.builder()
                .position(crewRequestDto.getPosition())
                .crewName(crewRequestDto.getCrewName())
                .user(user)
                .build();

        crewRepository.save(crew);
    }
}
