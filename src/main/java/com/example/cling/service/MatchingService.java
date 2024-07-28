package com.example.cling.service;

import com.example.cling.dto.UserResponseDto;
import com.example.cling.entity.Matching;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.MatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchingService {

    private final MatchingRepository matchingRepository;

    @Autowired
    public MatchingService( MatchingRepository matchingRepository) {
        this.matchingRepository = matchingRepository;
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllMatchings(String userId) {
        List<Matching> matchings = matchingRepository.findByStudent1_StudentIdOrStudent2_StudentId(userId, userId);
        List<UserResponseDto> matchingList = new ArrayList<>();

        for (Matching matching : matchings) {
            UserEntity student_1 = matching.getStudent1();
            UserEntity student_2 = matching.getStudent2();

            if (!userId.equals(student_1.getStudentId()))
                matchingList.add(UserResponseDto.toDto(student_1));
            else
                matchingList.add(UserResponseDto.toDto(student_2));
        }
        return matchingList;
    }

}
