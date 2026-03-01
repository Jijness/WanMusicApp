package com.example.backend.service.implement;

import com.example.backend.repository.FriendshipRepository;
import com.example.backend.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImp implements FriendshipService {

    private final FriendshipRepository friendshipRepo;

    @Override
    public int countFriendByUserId(Long userId) {
        return friendshipRepo.countFriendByUserId(userId);
    }
}
