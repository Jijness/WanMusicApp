package com.example.backend.mapper;

import com.example.backend.dto.playlist.PlaylistPreviewDTO;
import com.example.backend.entity.Playlist;
import com.example.backend.service.S3StorageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PlaylistMapper {

    @Autowired
    private S3StorageService s3StorageService;

    @Mapping(source = "thumbnailKey", target = "thumbnailUrl", qualifiedByName = "mapKeyToUrl")
    public abstract PlaylistPreviewDTO toPlaylistPreviewDTO(Playlist playlist);
    public abstract List<PlaylistPreviewDTO> toPlaylistPreviewDTOList(List<Playlist> playlistList);


    @Named("mapKeyToUrl")
    protected String mapKeyToUrl(String key){
        return s3StorageService.getGetPresignedUrl(key, "thumbnail");
    }
}
