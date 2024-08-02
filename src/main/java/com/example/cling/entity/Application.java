package com.example.cling.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String recruitingDepartment;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    @OneToMany(
            mappedBy = "application",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Attachment> application;

    @Column
    private boolean firstResult;

    @Column
    private boolean secondResult;

    public void addImage(Attachment file) {
        this.application.add(file);

        // 만약 게시글이 다를 경우? 게시글 정보 업데이트? (동기화)
        if (file.getApplication()!= this)
            file.setApplication(this);
    }
    public void updateResult(int step, boolean result) {
        if (step == 1) {
            this.firstResult = result;
        } else if (step == 2) {
            this.secondResult = result;
        } else {
            throw new IllegalArgumentException("Invalid step value: " + step);
        }
    }
}
