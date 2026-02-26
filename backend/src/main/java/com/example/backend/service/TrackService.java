package com.example.backend.service;

import com.example.backend.dto.track.TrackCreateDraftDTO;

public interface TrackService {
    Long createDraft(TrackCreateDraftDTO dto);
}
