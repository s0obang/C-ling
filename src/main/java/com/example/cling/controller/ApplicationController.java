package com.example.cling.controller;

import com.example.cling.dto.ApplicationDto;
import com.example.cling.service.ApplicationService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ApplicationController {

    private final ApplicationService applicationService;

//step은 현재 모집중인 단계
    @PutMapping("/updateResults")
    public ResponseEntity<String> updateResults(@RequestParam int step, @RequestParam int recruitingId, @RequestBody Map<String, Boolean> results) {
        try {
            applicationService.updateResults(step, results, recruitingId);
            return ResponseEntity.ok("Results updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/applications/{recruitingDepartment}")
    public List<ApplicationDto> getApplications(@PathVariable("recruitingDepartment") String recruitingDepartment) {

        return applicationService.getApplications(recruitingDepartment);

    }

    @GetMapping("/downloadStudentList")
    public ResponseEntity<Resource> downloadStudentList(
            @RequestParam("recruitingDepartment") String recruitingDepartment,
            @RequestParam("step") int step) {
        try {
            Resource resource = applicationService.generateStudentList(recruitingDepartment, step);

            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"student_list.txt\"")
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
