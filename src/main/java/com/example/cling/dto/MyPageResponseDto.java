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
}