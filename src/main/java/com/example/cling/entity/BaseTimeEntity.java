package com.example.cling.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @CreatedDate
    @Column(updatable = false)
    private String createdDate;

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now().format(formatter);
    }
}
