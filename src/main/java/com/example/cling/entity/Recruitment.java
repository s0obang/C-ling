package com.example.cling.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    @Column(nullable = false)
    private String step;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String dueDate;

    @Column(nullable = false)
    private boolean isCompleted;

    @Column(nullable = false)
    private String onStep = "1";

    @OneToMany(
            mappedBy = "recruitment",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Attachment> images = new ArrayList<>();

    @OneToMany(
            mappedBy = "recruitment",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Attachment> files = new ArrayList<>();


    public void addAttachment(Attachment attachment) {
        if (attachment.getFileType().equals("recruitment_image")) {
            this.images.add(attachment);
        } else if (attachment.getFileType().equals("recruitment_file")) {
            this.files.add(attachment);
        }

        if (attachment.getRecruitment() != this) {
            attachment.setRecruitment(this);
        }
    }

}
