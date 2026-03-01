package com.example.backend.mapper;

import com.example.backend.dto.AlbumDTO;
import com.example.backend.entity.Album;
import com.example.backend.service.S3StorageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AlbumMapper {

    @Autowired
    private S3StorageService s3StorageService;

    @Mapping(source = "thumbnailKey", target = "thumbnailUrl", qualifiedByName = "albumThumbnailKeyToUrl")
    @Mapping(source = "realeaseDate", target = "releaseYear", qualifiedByName = "albumDateToYear")
    public abstract AlbumDTO toAlbumDTO(Album album);

    public abstract List<AlbumDTO> toAlbumDTOList(List<Album> albumList);

    @Named("albumThumbnailKeyToUrl")
    protected String albumThumbnailKeyToUrl(String key){
        return s3StorageService.getGetPresignedUrl(key, "thumbnail");
    }

    @Named("albumDateToYear")
    protected int albumDateToYear(LocalDate date){
        return date.getYear();
    }
}
