package com.example.backend.service;

import com.example.backend.dto.playlist.PlaylistPreviewDTO;

import java.util.List;

public interface PlaylistService {
    List<PlaylistPreviewDTO> getPlaylistsByOwnerId(Long ownerId);
    int countPlaylistsByOwnerId(Long ownerId);
    String createPlaylist(String name);
    String deletePlaylist(Long playlistId);
}
