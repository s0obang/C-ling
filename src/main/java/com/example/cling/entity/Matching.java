package com.example.cling.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id_1", nullable = false)
    private UserEntity student1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id_2", nullable = false)
    private UserEntity student2;

    public Matching(UserEntity student_1, UserEntity student_2) {
        this.student1 = student_1;
        this.student2 = student_2;
    }
}
