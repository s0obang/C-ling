package com.example.cling.service;

import com.example.cling.dto.UserResponseDto;
import com.example.cling.entity.Matching;
import com.example.cling.entity.Request;
import com.example.cling.entity.UserEntity;
import com.example.cling.repository.MatchingRepository;
import com.example.cling.repository.RequestRepository;
import com.example.cling.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RequestService {
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final MatchingRepository matchingRepository;
    private final MatchingService matchingService;

    @Autowired
    public RequestService(UserRepository userRepository, RequestRepository requestRepository, MatchingRepository matchingRepository, MatchingService matchingService) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
        this.matchingRepository = matchingRepository;
        this.matchingService = matchingService;
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllRequests(String requestee_id) {

        List<Request> requests = requestRepository.findByRequestee_StudentId(requestee_id);
        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (Request request : requests) {
            UserEntity requester = request.getRequester();
            userResponseDtos.add(UserResponseDto.toDto(requester));
        }

        return userResponseDtos;
    }


    // 요청 보내기
    @Transactional
    public Request send(String requester_id, String requestee_id) {

        // 같은 요청 두 번 불가
        Optional<Integer> request_id = getFriendRequestId(requester_id, requestee_id);
        if (request_id.isPresent()) {
            throw new IllegalArgumentException("이미 요청을 보냈습니다.");
        }
        // 상대방으로부터 요청이 와있는 경우 요청 불가
        Optional<Integer> requestTest = getFriendRequestId(requestee_id, requester_id);
        if (requestTest.isPresent()) {
            throw new IllegalArgumentException("이미 상대방이 요청을 보냈습니다.");
        }

        // 이미 크링된 경우 요청 불가
        List<UserResponseDto> matchings = matchingService.getAllMatchings(requester_id);
        boolean alreadyMatched = matchings.stream()
                .anyMatch(user -> user.getStudentId().equals(requestee_id));
        if (alreadyMatched) {
            throw new IllegalArgumentException("이미 크링되었습니다.");
        }



        UserEntity requester = userRepository.findById(Long.valueOf(requester_id))
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + requester_id));
        UserEntity requestee = userRepository.findById(Long.valueOf(requestee_id))
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + requestee_id));

        Request request = new Request(requester, requestee);

        return requestRepository.save(request);

    }

    // 요청 거절
    @Transactional
    public void decline(String requester_id, String requestee_id) {

        Optional<Integer> request_id = getFriendRequestId(requester_id, requestee_id);

        int id;

        if (request_id.isPresent()) {
            id = request_id.get();
        } else {
            throw new IllegalArgumentException("Request doesn't exist in table");
        }

        if(requestRepository.existsById(id)) {
            requestRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Request not found with id " + request_id);
        }

    }

    public Optional<Integer> getFriendRequestId(String requesterId, String requesteeId) {
        return requestRepository.findIdByRequesterAndRequestee(requesterId, requesteeId);
    }

    // 요청 수락
    @Transactional
    public Matching accept(String requester_id, String requestee_id) {

        // id를 int로 꺼내기
        Optional<Integer> request_id = getFriendRequestId(requester_id, requestee_id);

        int id;
        if (request_id.isPresent()) {
            id = request_id.get();
        } else {
            throw new IllegalArgumentException("Request doesn't exist in table");
        }

        // id를 request 테이블에서 찾아서 지우고 matching 테이블로 보내기
        if(requestRepository.existsById(id)) {
            requestRepository.deleteById(id);

            UserEntity requester = userRepository.findById(Long.valueOf(requester_id))
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id " + requester_id));
            UserEntity requestee = userRepository.findById(Long.valueOf(requestee_id))
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id " + requestee_id));

            Matching matching = new Matching(requester, requestee);
            return matchingRepository.save(matching);
        } else {
            throw new IllegalArgumentException("Request not found with id " + request_id);
        }
    }

    @Transactional
    public List<UserResponseDto> clinkSame(String userId) {

        Optional<UserEntity> user = userRepository.findById(Long.valueOf(userId));
        UserEntity student;
        if (user.isPresent()) {
            student = user.get();
        } else {
            throw new IllegalArgumentException("다시 로그인 하세요");
        }

        String major = student.getMajor();
        List<UserEntity> sameMajorUsers = userRepository.findSameMajorUsers(major, userId);

        // 매칭된 사용자 찾기
        List<String> matchedUser = matchingService.getAllMatchings(userId)
                .stream()
                .map(UserResponseDto::getStudentId)
                .collect(Collectors.toList());

        // 학번 뒷자리 4자리 추출 함수
        Function<String, String> lastFourId = id -> {
            if (id != null && id.length() == 8) {
                return id.substring(id.length() - 4);
            }
            return "";
        };

        // 현재 사용자의 학번 뒷자리 4자리
        String userIdLast = lastFourId.apply(userId);

        // 매칭된 사용자 제외 및 학번 뒷자리 4자리 같은 사람만 필터링
        List<UserEntity> filteredUsers = sameMajorUsers.stream()
                .filter(userEntity -> !matchedUser.contains(userEntity.getStudentId()))
                .filter(userEntity -> lastFourId.apply(userEntity.getStudentId()).equals(userIdLast))
                .collect(Collectors.toList());

        return filteredUsers.stream()
                .map(UserResponseDto::toDto)
                .collect(Collectors.toList());

    }

    @Transactional
    public List<UserResponseDto> clinkOther(String userId) {

        Optional<UserEntity> user = userRepository.findById(Long.valueOf(userId));
        UserEntity student;
        if (user.isPresent()) {
            student = user.get();
        } else {
            throw new IllegalArgumentException("다시 로그인 하세요");
        }

        String major = student.getMajor();
        List<UserEntity> otherMajorUsers = userRepository.findOtherMajorUsers(major, userId);

        // 매칭된 사용자 찾아서
        List<String> matchedUser = matchingService.getAllMatchings(userId)
                .stream()
                .map(UserResponseDto::getStudentId)
                .collect(Collectors.toList());

        // 학번 뒷자리 2자리 추출 함수
        Function<String, String> lastTwoId = id -> {
            if (id != null && id.length() == 8) {
                return id.substring(id.length() - 2);
            }
            return "";
        };

        // 현재 사용자의 학번 뒷자리 2자리
        String userIdLast = lastTwoId.apply(userId);

        // 매칭된 사용자 제외 및 학번 뒷자리 2자리 같은 사람만 필터링
        List<UserEntity> filteredUsers = otherMajorUsers.stream()
                .filter(userEntity -> !matchedUser.contains(userEntity.getStudentId()))
                .filter(userEntity -> lastTwoId.apply(userEntity.getStudentId()).equals(userIdLast))
                .collect(Collectors.toList());

        return filteredUsers.stream()
                .map(UserResponseDto::toDto)
                .collect(Collectors.toList());

    }

}
