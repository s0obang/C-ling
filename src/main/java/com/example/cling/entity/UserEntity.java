package com.example.cling.entity;

import com.example.cling.dto.MyPageResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class UserEntity {

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Id
    @Column(name = "student_id", length = 8, nullable = false)
    private String studentId;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "major", length = 50, nullable = false)
    private String major;

    @Column(name = "position", length = 50) // 직책을 저장할 필드
    private String position;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private ProfileImage profileImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PositionRequest> positionRequests = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Crew> crews = new ArrayList<>();

    // 직책 리스트를 가져오기 위한 메소드
    public List<String> getPositions() {
        return positionRequests.stream()
                .map(PositionRequest::getPosition)
                .distinct()
                .collect(Collectors.toList());
    }

    // 크루명 리스트를 가져오기 위한 메소드
    public List<String> getCrewNames() {
        return crews.stream()
                .map(Crew::getCrewName)
                .distinct()
                .collect(Collectors.toList());
    }

    // 직책과 크루명 리스트를 가져오기 위한 메소드
    public List<MyPageResponseDto.PositionAndCrew> getPositionsAndCrews() {
        return crews.stream()
                .map(crew -> new MyPageResponseDto.PositionAndCrew(
                        crew.getPosition(),
                        crew.getCrewName() != null ? crew.getCrewName() : "" // crewName이 null인 경우 빈 문자열로 처리
                ))
                .collect(Collectors.toList());
    }

    public int getPositionCount() {
        return positionRequests.size();
    }
}
