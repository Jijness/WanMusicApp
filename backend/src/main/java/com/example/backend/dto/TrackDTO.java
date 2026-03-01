package com.example.backend.dto;

public record TrackDTO (
        Long id,
        String title,
        String trackUrl,
        String thumbnailUrl,
        int duration
) {}
