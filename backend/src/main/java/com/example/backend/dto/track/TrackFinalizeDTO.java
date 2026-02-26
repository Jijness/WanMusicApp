package com.example.backend.dto.track;

import lombok.Data;

import java.util.List;

@Data
public class TrackFinalizeDTO {
    private Long trackId;
    private List<Long> tagIds;
    private List<Long> featuredArtistIds;
}
