package com.example.cling.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovePositionDto {
    private String studentId;
    private String position;
    private String crewName;
}
