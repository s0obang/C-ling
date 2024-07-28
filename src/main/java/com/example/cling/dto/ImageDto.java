package com.example.cling.dto;

import com.example.cling.entity.Image;
import com.example.cling.entity.Notice;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private int id;
    @NotBlank
    private Notice notice;
    @NotBlank
    private String originImageName;
    @NotBlank
    private String imageName;
    @NotBlank
    private String imagePath;

    public static ImageDto toDto(Image image) {
        return new ImageDto(
                image.getId(),
                image.getNotice(),
                image.getOriginImageName(),
                image.getImageName(),
                image.getImagePath()
        );
    }
}
