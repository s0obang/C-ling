package com.example.cling.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="chat")
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private RoomEntity roomId;

    private String sender;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column
    private String fileName; // 파일 이름 (이미지 전송 시)

    @Column
    private String fileUrl; // 파일 경로나 URL (이미지 전송 시)

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime sendDate;
}
