package com.example.cling.service;

import com.example.cling.dto.PositionRequestDto;
import com.example.cling.entity.Crew;
import com.example.cling.entity.PositionRequest;
import com.example.cling.entity.PositionRequestStatus;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.CrewRepository;
import com.example.cling.repository.PositionRequestRepository;
import com.example.cling.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PositionRequestServiceImpl implements PositionRequestService {

    private final PositionRequestRepository positionRequestRepository;
    private final UserRepository userRepository;
    private final CrewRepository crewRepository;

    @Value("${position.image.path}")
    private String positionImagePath;

    @Override
    @Transactional
    public void processPositionRequest(PositionRequestDto positionRequestDto) {
        MultipartFile authenticationImage = positionRequestDto.getAuthenticationImage();
        String imageUrl = "";

        if (authenticationImage != null && !authenticationImage.isEmpty()) {
            try {
                String fileName = UUID.randomUUID() + "_" + authenticationImage.getOriginalFilename();
                File destinationFile = new File(positionImagePath + File.separator + fileName);

                // 파일을 저장
                authenticationImage.transferTo(destinationFile);

                // 저장된 파일의 URL 생성
                imageUrl = "/positionImages/" + fileName;

            } catch (IOException e) {
                throw new RuntimeException("직책 인증 이미지 저장 실패: " + e.getMessage());
            }
        }

        UserEntity user = userRepository.findByStudentId(positionRequestDto.getStudentId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 현재 요청에서 직책과 크루명 검증
        List<PositionRequest> currentRequests = positionRequestRepository.findByUser(user);

        long positionCount = currentRequests.stream()
                .map(PositionRequest::getPosition)
                .filter(Objects::nonNull)
                .distinct()
                .count();

        long crewCount = currentRequests.stream()
                .map(PositionRequest::getCrewName)
                .filter(Objects::nonNull)
                .distinct()
                .count();

        // 직책과 크루명이 각각 3개 이상인 경우 예외 발생
        if (positionCount >= 3 && !currentRequests.stream().anyMatch(req -> req.getPosition().equals(positionRequestDto.getPosition()))) {
            throw new RuntimeException("직책은 최대 3개까지만 등록 가능합니다.");
        }
        if (crewCount >= 3 && !currentRequests.stream().anyMatch(req -> req.getCrewName().equals(positionRequestDto.getCrewName()))) {
            throw new RuntimeException("크루명은 최대 3개까지만 등록 가능합니다.");
        }

        PositionRequest positionRequest = PositionRequest.builder()
                .name(positionRequestDto.getName())
                .major(positionRequestDto.getMajor())
                .studentId(positionRequestDto.getStudentId())
                .position(positionRequestDto.getPosition())
                .authenticationImage(imageUrl) // URL을 String으로 저장
                .status(PositionRequestStatus.PENDING)
                .user(user) // UserEntity 설정
                .crewName(positionRequestDto.getCrewName()) // 크루 이름 설정
                .build();

        positionRequestRepository.save(positionRequest);
    }

    @Override
    @Transactional
    public void approvePositionRequest(Long requestId) {
        PositionRequest positionRequest = positionRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("직책 인증 요청을 찾을 수 없습니다."));

        if (positionRequest.getStatus() != PositionRequestStatus.PENDING) {
            throw new RuntimeException("직책 인증 요청이 승인 대기 상태가 아닙니다.");
        }

        UserEntity user = userRepository.findByStudentId(positionRequest.getStudentId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 직책을 사용자에게 설정
        user.setPosition(positionRequest.getPosition());

        // 크루 이름이 요청에 포함되어 있으면 추가
        if (positionRequest.getCrewName() != null && !positionRequest.getCrewName().isEmpty()) {
            Crew crew = Crew.builder()
                    .position(positionRequest.getPosition())
                    .crewName(positionRequest.getCrewName())
                    .user(user)
                    .build();
            crewRepository.save(crew);
        }

        userRepository.save(user);

        // 직책 요청의 상태를 승인으로 업데이트
        positionRequest.setStatus(PositionRequestStatus.APPROVED);
        positionRequestRepository.save(positionRequest);
    }

    @Override
    @Transactional
    public void rejectPositionRequest(Long requestId) {
        PositionRequest positionRequest = positionRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("직책 인증 요청을 찾을 수 없습니다."));

        if (positionRequest.getStatus() != PositionRequestStatus.PENDING) {
            throw new RuntimeException("직책 인증 요청이 승인 대기 상태가 아닙니다.");
        }

        positionRequest.setStatus(PositionRequestStatus.REJECTED);
        positionRequestRepository.save(positionRequest);
    }
}