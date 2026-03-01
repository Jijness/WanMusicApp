package com.example.backend.dto.track;

import lombok.Data;

import java.util.Set;

@Data
public class TrackCreateDraftDTO {
    private String title;
    private String trackKey;
    private String thumbnailKey;
    private int duration;
    private Set<Long> tagIds;
    private Set<Long> featuredArtistIds;
}
