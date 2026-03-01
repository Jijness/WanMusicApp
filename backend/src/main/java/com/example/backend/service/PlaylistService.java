package com.example.backend.service;

import com.example.backend.dto.PlaylistPreviewDTO;

import java.util.List;

public interface PlaylistService {
    List<PlaylistPreviewDTO> getPlaylistsByOwnerId(Long ownerId);
    int countPlaylistsByOwnerId(Long ownerId);
}
