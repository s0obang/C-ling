package com.example.cling.dto;

import com.example.cling.entity.Application;
import com.example.cling.entity.Attachment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDto {
    private int id;
    @NotBlank(message = "recruitingDepartment cannot be blank")
    private String recruitingDepartment;
    @NotBlank(message = "studentId cannot be blank")
    private String studentId;
    @NotBlank(message = "studentName cannot be blank")
    private String studentName;
    @NotBlank(message = "application cannot be blank")
    private List<Attachment> application;


    public static ApplicationDto toDto(Application savedApplication) {

        return new ApplicationDto(
                savedApplication.getId(),
                savedApplication.getRecruitingDepartment(),
                savedApplication.getStudentId(),
                savedApplication.getStudentName(),
                savedApplication.getApplication()
        );
    }
}
