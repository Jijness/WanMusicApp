package com.example.backend.dto;

import lombok.Data;

@Data
public class AlbumDTO {
    private Long id;
    private String title;
    private String thumbnailUrl;
    private int  releaseYear;
}
