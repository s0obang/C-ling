package com.example.cling.service;

import com.example.cling.dto.CrewRequestDto;
import com.example.cling.dto.MyPageResponseDto;
import com.example.cling.dto.SignUpRequestDto;
import com.example.cling.entity.ProfileImage;
import com.example.cling.entity.UserEntity;
import com.example.cling.entity.Crew;
import com.example.cling.repository.ProfileImageRepository;
import com.example.cling.repository.UserRepository;
import com.example.cling.repository.CrewRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CrewRepository crewRepository;
    private final ProfileImageService profileImageService;
    private final PasswordEncoder passwordEncoder;

    @Value("${default.image.url}")
    private String defaultImageUrl;

    public UserEntity registerUser(SignUpRequestDto signUpRequestDto) {
        // studentId 중복 검사
        if (userRepository.findByStudentId(signUpRequestDto.getStudentId()).isPresent()) {
            throw new IllegalArgumentException("같은 학번으로 가입된 계정이 이미 존재합니다.");
        }

        UserEntity user = new UserEntity();
        user.setName(signUpRequestDto.getName());
        user.setStudentId(signUpRequestDto.getStudentId());
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setMajor(signUpRequestDto.getMajor());

        // 기본 프로필 이미지 설정
        ProfileImage profileImage = new ProfileImage();
        profileImage.setUrl(defaultImageUrl);
        profileImage.setUser(user);
        user.setProfileImage(profileImage);

        return userRepository.save(user);
    }

    public void updateProfileImage(String studentId, MultipartFile file) {
        profileImageService.upload(file, studentId);
    }

    public boolean loginUser(String studentId, String password) {
        UserEntity user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return passwordEncoder.matches(password, user.getPassword());
    }

    public MyPageResponseDto getUserInfo(String studentId) {
        UserEntity user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 프로필 이미지 URL 가져오기
        String profileImageUrl = user.getProfileImage() != null ? user.getProfileImage().getUrl() : defaultImageUrl;

        return new MyPageResponseDto(
                user.getName(),
                user.getStudentId(),
                profileImageUrl,
                user.getMajor(),
                null, // positions는 null로 설정
                null, // crewNames는 null로 설정
                null  // positionsAndCrews는 null로 설정
        );
    }

    public MyPageResponseDto getUserPositionAndCrew(String studentId) {
        UserEntity user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 사용자와 관련된 크루 데이터를 가져옵니다.
        List<Crew> crews = crewRepository.findByUser(user);

        // 프로필 이미지 URL을 가져옵니다.
        String profileImageUrl = user.getProfileImage() != null ? user.getProfileImage().getUrl() : defaultImageUrl;

        // PositionAndCrew 리스트를 생성합니다.
        List<MyPageResponseDto.PositionAndCrew> positionsAndCrews = crews.stream()
                .map(crew -> new MyPageResponseDto.PositionAndCrew(
                        crew.getPosition(),
                        crew.getCrewName() != null ? crew.getCrewName() : "" // crewName이 null인 경우 빈 문자열로 처리
                ))
                .collect(Collectors.toList());

        // positions 리스트를 생성합니다.
        List<String> positions = positionsAndCrews.stream()
                .map(MyPageResponseDto.PositionAndCrew::getPosition)
                .distinct()
                .collect(Collectors.toList());

        // crewNames 리스트를 생성합니다.
        List<String> crewNames = positionsAndCrews.stream()
                .map(MyPageResponseDto.PositionAndCrew::getCrewName)
                .distinct()  // 중복 제거
                .collect(Collectors.toList());

        MyPageResponseDto response = new MyPageResponseDto();
        response.setName(user.getName());
        response.setStudentId(user.getStudentId());
        response.setProfileImageUrl(profileImageUrl);
        response.setMajor(user.getMajor());
        response.setPositions(positions);   // positions 리스트 설정
        response.setCrewNames(crewNames);   // crewNames 리스트 설정
        response.setPositionsAndCrews(positionsAndCrews);

        return response;
    }

    public void updatePassword(String email, String newPassword) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public UserEntity getUserByStudentId(String studentId) {
        return userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with studentId: " + studentId));
    }

    public UserEntity getUserByStudentIdAndName(String studentId, String name) {
        return userRepository.findByStudentIdAndName(studentId, name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with studentId: " + studentId + " and name: " + name));
    }

    public void updatePosition(String studentId, String position) {
        UserEntity user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        user.setPosition(position);  // 직책을 저장하는 필드에 업데이트
        userRepository.save(user);
    }

    public String getLoggedInUsername(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getUsername();
    }

    public void addCrew(String studentId, CrewRequestDto crewRequestDto) {
        UserEntity user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        String crewName = crewRequestDto.getCrewName() != null ? crewRequestDto.getCrewName() : "";

        Crew crew = Crew.builder()
                .position(crewRequestDto.getPosition())
                .crewName(crewName)
                .user(user)
                .build();

        crewRepository.save(crew);
    }

    public String getUserPosition(String studentId) {
        UserEntity user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        return user.getPosition() != null ? user.getPosition() : "";
    }
}
