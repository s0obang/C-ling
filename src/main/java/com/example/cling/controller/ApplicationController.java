package com.example.cling.controller;

import com.example.cling.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ApplicationController {

    private final ApplicationService applicationService;

//step은 현재 모집중인 단계
    @PutMapping("/updateResults/{step}")
    public ResponseEntity<String> updateResults(@PathVariable int step, @RequestBody Map<String, Boolean> results) {
        try {
            applicationService.updateResults(step, results);
            return ResponseEntity.ok("Results updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }




}
