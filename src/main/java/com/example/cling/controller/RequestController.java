package com.example.cling.controller;

import com.example.cling.dto.UserResponseDto;
import com.example.cling.entity.Request;
import com.example.cling.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/request")
    public List<UserResponseDto> requestList(@AuthenticationPrincipal UserDetails userDetails) {
        String requestee_id = userDetails.getUsername();
        return requestService.getAllRequests(requestee_id);
    }

    @PostMapping("/request/send")
    public ResponseEntity<?> sendRequest(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("requestee_id") String requestee_id
                            ) {
        try {
            String requester_id = userDetails.getUsername();
            Request request = requestService.send(requester_id, requestee_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(request);
        } catch (IllegalArgumentException e) {
            // 이미 요청이 존재하는 경우 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/request/decline")
    public void declineRequest(
            @RequestParam("requester_id") String requester_id,
            @AuthenticationPrincipal UserDetails userDetails
                            ) {
        String requestee_id = userDetails.getUsername();
        requestService.decline(requester_id, requestee_id);

    }

    @PostMapping("/request/accept")
    public void acceptRequest(
            @RequestParam("requester_id") String requester_id,
            @AuthenticationPrincipal UserDetails userDetails
                            ) {
        String requestee_id = userDetails.getUsername();
        requestService.accept(requester_id, requestee_id);
    }

    @GetMapping("/clink-same")
    public List<UserResponseDto> clinkSame(@AuthenticationPrincipal UserDetails userDetails) {
        String userId = userDetails.getUsername();
        return requestService.clinkSame(userId);
    }

    @GetMapping("/clink-other")
    public List<UserResponseDto> clinkOther(@AuthenticationPrincipal UserDetails userDetails) {
        String userId = userDetails.getUsername();
        return requestService.clinkOther(userId);
    }

}
