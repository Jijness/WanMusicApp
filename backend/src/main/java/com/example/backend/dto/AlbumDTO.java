package com.example.backend.dto;

public record AlbumDTO (
        Long id,
        String title,
        String thumbnailUrl,
        int releaseYear
) {}
