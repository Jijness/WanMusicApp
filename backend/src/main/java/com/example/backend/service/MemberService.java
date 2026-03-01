package com.example.backend.service;

import com.example.backend.dto.user.MemberProfileDTO;
import com.example.backend.dto.user.UserUpdateProfileDTO;

public interface MemberService {
    String updateProfile(UserUpdateProfileDTO dto);
    MemberProfileDTO getProfile(Long memberId);
}
