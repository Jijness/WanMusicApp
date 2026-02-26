package com.example.backend.service.implement;

import com.example.backend.Enum.TrackStatus;
import com.example.backend.dto.track.TrackCreateDraftDTO;
import com.example.backend.entity.Track;
import com.example.backend.repository.ArtistProfileRepository;
import com.example.backend.repository.TrackRepository;
import com.example.backend.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackServiceImp implements TrackService {

    private final TrackRepository trackRepo;
    private final ArtistProfileRepository artistProfileRepo;

    @Override
    public Long createDraft(TrackCreateDraftDTO dto) {
        Track track = new Track();
        track.setTitle(dto.getTitle());
        track.setFileKey(dto.getTrackKey());
        track.setThumbnailKey(dto.getThumbnailKey());
        track.setDuration(dto.getDuration());
        track.setStatus(TrackStatus.DRAFT);
        return trackRepo.save(track).getId();
    }
}
