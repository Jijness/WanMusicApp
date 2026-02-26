package com.example.backend.dto.track;

import com.example.backend.dto.TagDTO;
import com.example.backend.dto.UserPreviewDTO;
import lombok.Data;

import java.util.List;

@Data
public class TrackDraftResponseDTO {
    private Long trackId;
    private String title;
    private String trackKey;
    private String thumbnailKey;
    private List<TagDTO> recommendedTags;
    private List<UserPreviewDTO> featuredArtists;
}
