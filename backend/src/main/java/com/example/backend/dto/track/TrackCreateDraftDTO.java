package com.example.backend.dto.track;

import com.example.backend.dto.UserPreviewDTO;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TrackCreateDraftDTO {
    private String title;
    private String trackKey;
    private String thumbnailKey;
    private int duration;
}
