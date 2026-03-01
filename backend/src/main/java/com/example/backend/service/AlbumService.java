package com.example.backend.service;

import com.example.backend.dto.AlbumDTO;
import com.example.backend.dto.PageResponse;

import java.util.List;

public interface AlbumService {
    PageResponse<AlbumDTO> getAlbumsByArtistId(Long artistId);
}
