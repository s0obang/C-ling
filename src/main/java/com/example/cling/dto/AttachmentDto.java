package com.example.cling.dto;

import com.example.cling.entity.Attachment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDto {
    @NotBlank
    private String originAttachmentName;
    @NotBlank
    private String attachmentName;
    @NotBlank
    private String attachmentUrl;
    @NotBlank
    private String fileType;

    public static AttachmentDto toDto(Attachment file) {
        return new AttachmentDto(
                file.getOriginAttachmentName(),
                file.getAttachmentName(),
                file.getAttachmentUrl(),
                file.getFileType()
        );
    }
}
