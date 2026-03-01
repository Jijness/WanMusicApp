package com.example.backend.service.implement;

import com.example.backend.dto.PlaylistPreviewDTO;
import com.example.backend.mapper.PlaylistMapper;
import com.example.backend.repository.PlaylistRepository;
import com.example.backend.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImp implements PlaylistService {

    private final PlaylistMapper playlistMapper;
    private final PlaylistRepository playlistRepo;

    @Override
    public List<PlaylistPreviewDTO> getPlaylistsByOwnerId(Long ownerId) {
        return playlistMapper.toPlaylistPreviewDTOList(playlistRepo.findAllByOwnerId(ownerId).orElseThrow(()-> new RuntimeException("Playlist not found!")));
    }

    @Override
    public int countPlaylistsByOwnerId(Long ownerId) {
        return playlistRepo.countByOwnerId(ownerId);
    }
}
