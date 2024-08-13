package com.example.cling.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageResponseDto {
    private String name;
    private String studentId;
    private String profileImageUrl;
    private String major;
    private List<String> positions;  // 직책 리스트
    private List<String> crewNames;  // 크루명 리스트
    private List<PositionAndCrew> positionsAndCrews;  // 직책과 크루명 리스트

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PositionAndCrew {
        private String position;
        private String crewName;
    }

    // 직책 또는 크루명 리스트의 크기를 검증하는 메서드
    public void validatePositionsAndCrewNames() {
        if (positions != null && positions.size() >= 3) {
            throw new RuntimeException("직책은 최대 3개까지만 등록 가능합니다.");
        }
        if (crewNames != null && crewNames.size() >= 3) {
            throw new RuntimeException("크루명은 최대 3개까지만 등록 가능합니다.");
        }
    }
}
