package com.example.cling.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "notice_id", nullable = false)
    @JsonBackReference
    private Notice notice;

    @Column(nullable = false)
    private String originImageName;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String imagePath;


    @Builder
    public Image (String originImageName, String imageName, String imagePath, Notice notice) {
        this.originImageName = originImageName;
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.setNotice(notice);
    }

    // 게시글 정보 저장 (동기화)
    public void setNotice(Notice notice) {
        this.notice = notice;

        if (!notice.getImages().contains(this))
            notice.getImages().add(this);
    }

}
