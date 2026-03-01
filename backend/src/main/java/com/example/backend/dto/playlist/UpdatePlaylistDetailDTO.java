package com.example.backend.dto.playlist;

public record UpdatePlaylistDetailDTO(
        String name,
        String description,
        String thumbnailKey
) {
}
