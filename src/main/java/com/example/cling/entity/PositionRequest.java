package com.example.cling.entity;

import com.example.cling.entity.PositionRequestStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "position_request")
public class PositionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "major", length = 50, nullable = false)
    private String major;

    @Column(name = "student_id", length = 8, nullable = false)
    private String studentId;

    @Column(name = "position", length = 50, nullable = false)
    private String position;

    @Column(name = "authentication_image", length = 255) // URL을 저장하기 위한 필드
    private String authenticationImage; // 이미지 URL을 저장할 필드

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private PositionRequestStatus status; // 인증 요청


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
