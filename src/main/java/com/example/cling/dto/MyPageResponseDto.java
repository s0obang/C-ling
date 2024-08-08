package com.example.cling.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponseDto {
    private String name;
    private String studentId;
    private String profileImageUrl;
    private String major;
    private String position;
    private String crewName;
    private List<PositionAndCrew> positionsAndCrews;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PositionAndCrew {
        private String position;
        private String crewName;
    }
}
