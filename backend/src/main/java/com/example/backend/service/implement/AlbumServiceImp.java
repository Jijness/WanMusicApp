package com.example.backend.service.implement;

import com.example.backend.dto.AlbumDTO;
import com.example.backend.dto.PageResponse;
import com.example.backend.entity.Album;
import com.example.backend.mapper.AlbumMapper;
import com.example.backend.mapper.PageMapper;
import com.example.backend.repository.AlbumRepository;
import com.example.backend.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumServiceImp implements AlbumService {

    private final AlbumMapper albumMapper;
    private final AlbumRepository albumRepo;
    private final PageMapper pageMapper;

    @Override
    public PageResponse<AlbumDTO> getAlbumsByArtistId(Long artistId) {
        Page<Album> albums = albumRepo.findByArtistId(artistId, Pageable.ofSize(4));
        return pageMapper.toPageResponse(albums, albumMapper::toAlbumDTO);
    }
}
