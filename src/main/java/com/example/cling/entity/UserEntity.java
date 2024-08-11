package com.example.cling.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    public int getPositionCount() {
        return positionRequests.size();
    }
}