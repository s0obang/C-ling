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
    private int id;
    @NotBlank
    private String originAttachmentName;
    @NotBlank
    private String attachmentUrl;

    public static AttachmentDto toDto(Attachment file) {
        return new AttachmentDto(
                file.getId(),
                file.getOriginAttachmentName(),
                file.getAttachmentUrl()
        );
    }
}
