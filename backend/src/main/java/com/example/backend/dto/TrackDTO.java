package com.example.backend.dto;

import lombok.Data;

@Data
public class TrackDTO {
    private Long id;
    private String title;
    private String trackUrl;
    private String thumbnailUrl;
    private int duration;
}
