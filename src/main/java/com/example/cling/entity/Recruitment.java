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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String step;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String dueDate;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT '1'", nullable = true)
    private String onStep;

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

    @OneToMany(
            mappedBy = "recruitment",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Application> applications = new ArrayList<>();


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

    public void addApplication(Application application) {
            this.applications.add(application);

        if (application.getRecruitment() != this) {
            application.setRecruitment(this);
        }
    }
    public void updateOnStep(int step) {
        if (step == 1) {
            this.onStep = "2";
        } else if (step == 2) {
            this.onStep = "3";
        }

    }



}
