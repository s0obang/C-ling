package com.example.cling.service;


import com.example.cling.controller.ChatRoomController;
import com.example.cling.dto.SendImageDto;
import com.example.cling.entity.ChatEntity;
import com.example.cling.entity.RoomEntity;
import com.example.cling.repository.ChatRepository;
import com.example.cling.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final RoomRepository roomRepository;

     public RoomEntity findRoomByRoomName(String roomName) {

         if (roomRepository.findByRoomName(roomName).isPresent()){
             return roomRepository.findByRoomName(roomName).get();
         } //이미 채팅방 존재하면 걍 roomEntity리턴
         else {
             return createRoom(roomName);
         } //없으면 만들고 리턴함
     }

    //채팅방 만드는거
    public RoomEntity createRoom(String roomName) {
        return roomRepository.save(
                RoomEntity.builder()
                        .roomName(roomName)
                        .build()
        );
    }

    public ChatEntity createChat(Long roomId, String sender, String message) {
        RoomEntity room = roomRepository.findById(roomId).orElseThrow();  //방 찾기 -> 없는 방일 경우 여기서 예외처리
        return chatRepository.save(ChatEntity.builder()
                .roomId(room)
                .sender(sender)
                .message(message)
                .sendDate(LocalDateTime.now())
                .build()
        );
    }
    public List<ChatEntity> findAllChatByRoomId(RoomEntity roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }
    public String roomNameMaker(String id1, String id2) {
        String roomName;
        if (id1.compareTo(id2) <= 0) {
            roomName = id1 + id2;
        } else {
            roomName= id2 + id1;
        }
        return roomName;
    }
    public RoomEntity findByRoomId(Long roomId){
        return roomRepository.findByRoomId(roomId);
    }

    public ChatEntity sendImage(SendImageDto sendImageDTO) throws IOException {
        RoomEntity room = roomRepository.findById(sendImageDTO.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid room ID"));

        MultipartFile imageFile = sendImageDTO.getImageFile();
        String fileName = imageFile.getOriginalFilename();
        String fileUrl = saveFile(imageFile);

        return chatRepository.save(ChatEntity.builder()
                .roomId(room)
                .sender(sendImageDTO.getSender())
                .fileName(fileName)
                .fileUrl(fileUrl)
                .sendDate(LocalDateTime.now())
                .build());
    }

    private String saveFile(MultipartFile file) throws IOException {
        // 저장할 경로 설정
        String uploadDir = "/home/ubuntu/uploads/chat"; // EC2 환경에서 uploads/chat 디렉토리
        Path uploadPath = Paths.get(uploadDir);

        // 디렉토리가 존재하지 않으면 생성
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 파일 이름 가져오기
        String fileName = file.getOriginalFilename();

        // 파일 저장 경로 설정
        Path filePath = uploadPath.resolve(fileName);

        // 파일을 저장 (덮어쓰기 방지를 위해 StandardCopyOption 설정)
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // 저장된 파일의 경로를 반환 (URL 형태가 아닌 파일 시스템 경로 반환)
        return filePath.toString();
    }

    public byte[] getImageBytes(String fileUrl) throws IOException {
        File file = new File(fileUrl);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found");
        }
        return Files.readAllBytes(file.toPath());
    }
}
