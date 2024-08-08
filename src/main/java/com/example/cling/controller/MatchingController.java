package com.example.cling.controller;

import com.example.cling.dto.UserResponseDto;
import com.example.cling.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchingController {

    private final MatchingService matchingService;
    @Autowired
    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    //연결된 수정이 불러오기
    @GetMapping("/matching")
    public List<UserResponseDto> matchingList(
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        String userId = userDetails.getUsername();
        return matchingService.getAllMatchings(userId);
    }
}
