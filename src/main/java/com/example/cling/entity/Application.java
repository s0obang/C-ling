package com.example.cling.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Attachment application;

    @Column(nullable = false)
    private boolean firstResult;

    @Column(nullable = false)
    private boolean secondResult;

}
