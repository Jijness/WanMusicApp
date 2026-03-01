package com.example.backend.service.implement;

import com.example.backend.dto.user.MemberProfileDTO;
import com.example.backend.dto.user.UserUpdateProfileDTO;
import com.example.backend.entity.Member;
import com.example.backend.mapper.MemberMapper;
import com.example.backend.repository.ArtistProfileRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.FollowerService;
import com.example.backend.service.FriendshipService;
import com.example.backend.service.MemberService;
import com.example.backend.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService {

    private final MemberMapper memberMapper;
    private final UserRepository userRepo;
    private final AuthenticationServiceImp authenticationService;
    private final FollowerService followerService;
    private final PlaylistService playlistService;
    private final FriendshipService friendshipService;


    @Override
    @Transactional
    public String updateProfile(UserUpdateProfileDTO dto) {
        Long currentUserId = authenticationService.getCurrentMember();

        Member currentMember = (Member) userRepo.findById(currentUserId).orElseThrow(()-> new RuntimeException("Member not found!")) ;

        currentMember.setAvatarKey(dto.getAvatarKey());
        currentMember.setFullName(dto.getDisplayName());
        return "Profile updated successfully!";
    }

    @Override
    public MemberProfileDTO getProfile(Long memberId) {
        Member member = (Member) userRepo.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found!"));

        MemberProfileDTO memberProfileDTO = memberMapper.toProfileDTO(member);

        memberProfileDTO.setFollowedArtistCount(followerService.countFollowedArtistByUserId(memberId));
        memberProfileDTO.setFriendCount(friendshipService.countFriendByUserId(memberId));
        memberProfileDTO.setPlaylistCount(playlistService.countPlaylistsByOwnerId(memberId));

        return memberProfileDTO;
    }
}
