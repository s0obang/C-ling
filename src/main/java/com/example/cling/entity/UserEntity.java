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

    @Column(name = "profile_image", length = 255)
    private String profileImage;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Id
    @Column(name = "student_id", length = 8, nullable = false)
    private String studentId;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;
}