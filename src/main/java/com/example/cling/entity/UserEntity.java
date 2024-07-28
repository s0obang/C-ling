package com.example.cling.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private ProfileImage profileImage;

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
        if (profileImage != null) {
            profileImage.setUser(this); // 양방향 설정
        }
    }

    @Column(name = "major", length = 50, nullable = false)
    private String major;
}