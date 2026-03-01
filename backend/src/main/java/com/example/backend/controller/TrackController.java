package com.example.backend.controller;

import com.example.backend.dto.track.TrackCreateDraftDTO;
import com.example.backend.dto.track.TrackDraftResponseDTO;
import com.example.backend.dto.track.TrackSubmitDTO;
import com.example.backend.service.TrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/track")
public class TrackController {

    private final TrackService trackService;

    @PostMapping("/submitDraft")
    public ResponseEntity<TrackDraftResponseDTO> submitDraft(TrackCreateDraftDTO dto){
        return ResponseEntity.ok(trackService.createDraft(dto));
    }

    @PostMapping("/submitFinalize")
    public ResponseEntity<String> submitFinalize(TrackSubmitDTO dto){
        return ResponseEntity.ok(trackService.submitTrack(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTrack(Long id){
        return ResponseEntity.ok(trackService.deleteTrack(id));
    }
}
