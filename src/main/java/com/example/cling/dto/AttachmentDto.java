package com.example.cling.dto;

import com.example.cling.entity.Attachment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDto {
    private int id;
    @NotBlank
    private String originAttachmentName;
    @NotBlank
    private String attachmentUrl;
    @NotBlank
    private byte[] attachmentByteList;

    public static AttachmentDto toDto(Attachment file) throws IOException {
        byte[] attachmentBytes = AttachmentDto.getAttachmentBytes(file.getAttachmentPath());
        return new AttachmentDto(
                file.getId(),
                file.getOriginAttachmentName(),
                file.getAttachmentUrl(),
                attachmentBytes
        );
    }

    public static byte[] getAttachmentBytes(String fileUrl) throws IOException {
        File file = new File(fileUrl);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found");
        }
        return Files.readAllBytes(file.toPath());
    }
}
