package com.example.backend.service;

import com.example.backend.dto.user.ArtistProfileDTO;
import com.example.backend.dto.user.UserUpdateProfileDTO;

public interface ArtistProfileService {
    ArtistProfileDTO getProfile(Long artistId);
    String updateProfile(UserUpdateProfileDTO dto);
}
