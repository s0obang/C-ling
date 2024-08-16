package com.example.cling.dto;

import com.example.cling.entity.Recruitment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentInfoDto {
    @NotBlank(message = "RecruitingDepartment cannot be blank")
    private String RecruitingDepartment;
    @NotBlank(message = "RecruitingDepartment cannot be blank")
    private int id;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "startDate cannot be blank")
    private String startDate;
    @NotBlank(message = "DueDate cannot be blank")
    private String dueDate;
    @NotBlank(message = "Step cannot be blank")
    private String step;
    @NotBlank(message = "Step cannot be blank")
    private String onStep;

    public static RecruitmentInfoDto toDto(Recruitment recruitment) {
        return new RecruitmentInfoDto(
                recruitment.getRecruitingDepartment(),
                recruitment.getId(),
                recruitment.getTitle(),
                recruitment.getStartDate(),
                recruitment.getDueDate(),
                recruitment.getStep(),
                recruitment.getOnStep()
        );
    }

}
