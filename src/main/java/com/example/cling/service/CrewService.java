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

        // 사용자의 직책이 요청된 직책과 일치하는지 확인
        if (!user.getPosition().equals(crewRequestDto.getPosition())) {
            throw new RuntimeException("사용자의 직책이 요청된 직책과 일치하지 않습니다.");
        }

        Crew crew = Crew.builder()
                .position(crewRequestDto.getPosition())
                .crewName(crewRequestDto.getCrewName())
                .user(user)
                .build();

        crewRepository.save(crew);
    }

    public void deleteCrew(String studentId, String crewName) {
        UserEntity user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Crew crew = crewRepository.findByUser(user).stream()
                .filter(c -> c.getCrewName().equals(crewName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("크루명을 찾을 수 없습니다."));

        crewRepository.delete(crew);
    }
}
