package com.example.backend.service;

import com.example.backend.dto.track.TrackCreateDraftDTO;
import com.example.backend.dto.track.TrackDraftResponseDTO;
import com.example.backend.dto.track.TrackSubmitDTO;

public interface TrackService {

    TrackDraftResponseDTO createDraft(TrackCreateDraftDTO dto);
    String submitTrack(TrackSubmitDTO dto);
    String deleteTrack(Long trackId);
}
