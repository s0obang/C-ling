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
    @NotBlank
    private String originImageName;
    @NotBlank
    private String imageName;
    @NotBlank
    private String imageUrl;

    public static ImageDto toDto(Image image) {
        return new ImageDto(
                image.getOriginImageName(),
                image.getImageName(),
                image.getImageUrl()
        );
    }
}
