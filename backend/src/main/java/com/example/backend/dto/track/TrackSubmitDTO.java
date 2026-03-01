package com.example.backend.dto.track;

import lombok.Data;

import java.util.Set;

@Data
public class TrackSubmitDTO {
    private Long id;
    private Set<Long> artistIds;
    private Set<Long> tagIds;
}
