package com.example.backend.service.implement;

import com.example.backend.dto.user.ArtistProfileDTO;
import com.example.backend.dto.user.UserUpdateProfileDTO;
import com.example.backend.entity.Album;
import com.example.backend.entity.ArtistProfile;
import com.example.backend.mapper.ArtistProfileMapper;
import com.example.backend.repository.ArtistProfileRepository;
import com.example.backend.service.AlbumService;
import com.example.backend.service.ArtistProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArtistProfileServiceImp implements ArtistProfileService {

    private final ArtistProfileMapper artistProfileMapper;
    private final AlbumService albumService;
    private final ArtistProfileRepository artistProfileRepo;
    private final AuthenticationServiceImp authenticationService;

    @Override
    public ArtistProfileDTO getProfile(Long artistId) {
        ArtistProfile profile = artistProfileRepo.findById(artistId).orElseThrow(()-> new RuntimeException("Artist profile not found!"));
        ArtistProfileDTO dto = artistProfileMapper.toArtistProfileDTO(profile);
        dto.setAlbums(albumService.getAlbumsByArtistId(artistId));
        return dto;
    }

    @Override
    @Transactional
    public String updateProfile(UserUpdateProfileDTO dto) {
        Long currentUserId = authenticationService.getCurrentMember();

        ArtistProfile profile = artistProfileRepo.findByMemberId(currentUserId).orElseThrow(()-> new RuntimeException("Artist profile not found!"));

        profile.setAvatarKey(dto.getAvatarKey());
        profile.setStageName(dto.getDisplayName());
        return "Profile updated successfully!";
    }
}
