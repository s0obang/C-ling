package com.example.cling.service;

import com.example.cling.dto.PositionRequestDto;

public interface PositionRequestService {
    void processPositionRequest(PositionRequestDto positionRequestDto);
    void approvePositionRequest(Long requestId);
    void rejectPositionRequest(Long requestId);
}
