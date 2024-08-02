package com.example.cling.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(nullable = false)
    private String originAttachmentName;

    @Column(nullable = false)
    private String attachmentName;

    @Column(nullable = false)
    private String attachmentPath;

    @Column(nullable = false)
    private String attachmentUrl;

    @Column(nullable = false)
    private String fileType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "notice_id")
    @JsonBackReference
    private Notice notice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recruitment_id")
    @JsonBackReference
    private Recruitment recruitment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id")
    @JsonBackReference
    private Application application;

    @Builder
    public Attachment(String originAttachmentName, String attachmentName, String attachmentPath, String attachmentUrl, String fileType, Notice notice, Recruitment recruitment, Application application) {
        this.originAttachmentName = originAttachmentName;
        this.attachmentName = attachmentName;
        this.attachmentPath = attachmentPath;
        this.attachmentUrl = attachmentUrl;
        this.fileType = fileType;
        this.notice = notice;
        this.recruitment = recruitment;
        this.application = application;
    }

    // 리크루팅 공고 정보 저장 (동기화)
    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;

        if (!recruitment.getImages().contains(this))
            recruitment.getImages().add(this);
    }

    // 게시글 정보 저장 (동기화)
    public void setNotice(Notice notice) {
        this.notice = notice;

        if (!notice.getImages().contains(this))
            notice.getImages().add(this);
    }

    // 게시글 정보 저장 (동기화)
    public void setApplication(Application application) {
        this.application = application;
    }

}
