package com.example.backend.service.implement;

import com.example.backend.dto.jam.CreateJamSessionRequestDTO;
import com.example.backend.dto.jam.JamPreviewDTO;
import com.example.backend.dto.jam.UpdateJamSessionRequestDTO;
import com.example.backend.entity.JamPlayerState;
import com.example.backend.entity.JamSession;
import com.example.backend.repository.*;
import com.example.backend.service.AuthenticationService;
import com.example.backend.service.JamSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JamSessionServiceImp implements JamSessionService {

    private final JamPlayerStateRepository jamPlayerStateRepo;
    private final JamSessionRepository jamSessionRepo;
    private final JamNotificationRepository jamNotificationRepo;
    private final JamParticipantRepository jamParticipantRepo;
    private final AuthenticationService authenticationService;
    private final MemberRepository memberRepo;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JamPreviewDTO createJamSession(CreateJamSessionRequestDTO dto) {
        JamSession jamSession = new JamSession();
        jamSession.setSessionCode(UUID.randomUUID().toString());
        jamSession.setOwner(memberRepo.findById(authenticationService.getCurrentMemberId()).get());
        jamSession.setSize(dto.getSize());
        jamSession.setPublic(!dto.isPrivate());

        try{
            jamSession = jamSessionRepo.save(jamSession);

            JamPlayerState jamPlayerState = new JamPlayerState();
            jamPlayerState.setJamSession(jamSession);
            jamPlayerState.setPlaying(false);
            jamPlayerState.setCurrentSeekPosition(0);
            jamPlayerState.setLastUpdated(LocalDateTime.now());

            jamSession.setPlayerState(jamPlayerState);

            jamPlayerStateRepo.save(jamPlayerState);
        } catch (Exception e){
            if(e.getMessage().contains("Duplicate entry")){
                throw new RuntimeException("You've already created a jam session!");
            }
        }

        JamPreviewDTO jamPreviewDTO = new JamPreviewDTO();
        jamPreviewDTO.setId(jamSession.getId());
        jamPreviewDTO.setCode(jamSession.getSessionCode());

        return jamPreviewDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateJamSession(UpdateJamSessionRequestDTO dto) {
        JamSession jamSession = jamSessionRepo.findById(dto.jamSessionId()).get();
        jamSession.setPublic(dto.isPublic());
        jamSession.setSize(dto.size());

        return "Updated jam session successfully!";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteJamSession(Long jamSessionId) {
        JamSession jamSession = jamSessionRepo.findById(jamSessionId).get();

        jamNotificationRepo.deleteByJamSession_Id(jamSessionId);
        jamParticipantRepo.deleteBySession_Id(jamSessionId);
        jamSessionRepo.delete(jamSession);

        return "Deleted jam session successfully!";
    }
}
