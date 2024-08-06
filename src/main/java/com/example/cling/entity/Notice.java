package com.example.cling.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notice extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToMany(
            mappedBy = "notice",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Attachment> images = new ArrayList<>();

    //이미지 리스트에 이미지 추가
    public void addImage(Attachment image) {
        this.images.add(image);

        // 만약 게시글이 다를 경우? 게시글 정보 업데이트? (동기화)
        if (image.getNotice() != this)
            image.setNotice(this);
    }

    // 이미지 리스트에서 이미지 삭제
    public void removeImage(Attachment image) {
        if (images.remove(image)) {
            image.setNotice(null);
        }
    }

    // 이미지 리스트 불러오기
    public List<Attachment> getImages() {
        return images;
    }

}
