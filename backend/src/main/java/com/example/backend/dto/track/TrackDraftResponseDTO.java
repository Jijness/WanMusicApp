package com.example.backend.dto.track;

import com.example.backend.dto.TagDTO;
import com.example.backend.dto.user.UserPreviewDTO;
import lombok.Data;

import java.util.List;

@Data
public class TrackDraftResponseDTO {
    private Long trackId;
    private String title;
    private String trackUrl;
    private String thumbnailUrl;
    private List<TagDTO> recommendedTags;
    private List<UserPreviewDTO> featuredArtists;
}
