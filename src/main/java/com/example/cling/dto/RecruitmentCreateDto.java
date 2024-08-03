package com.example.cling.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentCreateDto {
    @NotBlank(message = "RecruitingDepartment cannot be blank")
    private String RecruitingDepartment;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Content cannot be blank")
    private String content;
    @NotBlank(message = "Step cannot be blank")
    private String step;
    @NotBlank(message = "startDate cannot be blank")
    private String startDate;
    @NotBlank(message = "DueDate cannot be blank")
    private String dueDate;

}