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
public class Recruitment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String recruitingDepartment;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToMany(
            mappedBy = "recruitment",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Attachment> images;

    @OneToMany(
            mappedBy = "recruitment",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Attachment> files;

    public void addImage(Attachment image) {
        this.images.add(image);

        // 만약 게시글이 다를 경우? 게시글 정보 업데이트? (동기화)
        if (image.getRecruitment() != this)
            image.setRecruitment(this);
    }

    public void addFile(Attachment file) {
        this.files.add(file);

        // 만약 게시글이 다를 경우? 게시글 정보 업데이트? (동기화)
        if (file.getRecruitment() != this)
            file.setRecruitment(this);
    }

}
