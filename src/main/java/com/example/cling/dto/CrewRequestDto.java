package com.example.cling.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrewRequestDto {
    private String position;
    private String crewName;
}
