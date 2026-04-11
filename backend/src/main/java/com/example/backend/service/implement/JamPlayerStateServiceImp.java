package com.example.backend.service.implement;

import com.example.backend.Enum.InteractionType;
import com.example.backend.Enum.JamInteractionStatus;
import com.example.backend.dto.jam.JamNotificationDTO;
import com.example.backend.dto.jam.JamTrackPreviewDTO;
import com.example.backend.dto.jam.UpdateJamPlayerRequestDTO;
import com.example.backend.dto.track.TrackPreviewDTO;
import com.example.backend.entity.JamNotification;
import com.example.backend.entity.JamPlayerState;
import com.example.backend.repository.JamNotificationRepository;
import com.example.backend.repository.JamPlayerStateRepository;
import com.example.backend.service.JamPlayerStateService;
import com.example.backend.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JamPlayerStateServiceImp implements JamPlayerStateService {

    private final JamNotificationRepository jamNotificationRepo;
    private final JamPlayerStateRepository jamPlayerStateRepo;
    private final TrackService trackService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateJamPlayerState(Long jamSessionId, int currentSeekPosition, boolean isPlaying) {
        JamPlayerState jamPlayerState = jamPlayerStateRepo.findByJamSessionId(jamSessionId).orElseThrow(() -> new RuntimeException("JamState not found!"));
        jamPlayerState.setCurrentSeekPosition(currentSeekPosition);
        jamPlayerState.setPlaying(isPlaying);
        jamPlayerState.setLastUpdated(LocalDateTime.now());
    }

    @Override
    public void rejectJamMemberRequest(UpdateJamPlayerRequestDTO dto) {
        JamNotification jamNotification = jamNotificationRepo.findById(dto.jamNotificationId()).orElseThrow(() -> new RuntimeException("JamNotification not found!"));
        jamNotification.setStatus(JamInteractionStatus.REJECTED);

        JamNotificationDTO response = new JamNotificationDTO();
        response.setJamSessionId(dto.jamId());
        response.setJamNotificationId(dto.jamNotificationId());
        response.setStatus(JamInteractionStatus.REJECTED);

        simpMessagingTemplate.convertAndSend("jam/notification/" + dto.jamId(), response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void acceptJamMemberRequest(UpdateJamPlayerRequestDTO dto) {
        JamNotification jamNotification = jamNotificationRepo.findById(dto.jamNotificationId()).orElseThrow(() -> new RuntimeException("JamNotification not found!"));
        jamNotification.setStatus(JamInteractionStatus.ACCEPTED);

        JamNotificationDTO notificationResponse = new JamNotificationDTO();
        notificationResponse.setJamSessionId(dto.jamId());
        notificationResponse.setJamNotificationId(dto.jamNotificationId());
        notificationResponse.setStatus(JamInteractionStatus.ACCEPTED);

        JamTrackPreviewDTO jamTrack = new JamTrackPreviewDTO();

        if(dto.interactionType().equals(InteractionType.PICK) ||
            dto.interactionType().equals(InteractionType.SKIP) ||
            dto.interactionType().equals(InteractionType.PREVIOUS)
        ){
            TrackPreviewDTO trackDTO = trackService.getTrack(dto.trackId());
            jamTrack = new JamTrackPreviewDTO(trackDTO);
            jamTrack.setPlaying(true);
            jamTrack.setCurrentSeekPosition(0);
        }else if(dto.interactionType().equals(InteractionType.PLAY)){
            jamTrack.setId(dto.trackId());
            jamTrack.setPlaying(true);
            jamTrack.setCurrentSeekPosition(dto.seekPosition());
        }else if(dto.interactionType().equals(InteractionType.PAUSE)){
            jamTrack.setId(dto.trackId());
            jamTrack.setPlaying(false);
        }else if(dto.interactionType().equals(InteractionType.JUMP)){
            jamTrack.setId(dto.trackId());
            jamTrack.setCurrentSeekPosition(dto.seekPosition());
        }

        simpMessagingTemplate.convertAndSend("jam/notification/" + dto.jamId(), notificationResponse);
        simpMessagingTemplate.convertAndSend("/jam/track/" + dto.jamId(), jamTrack);

    }
}
