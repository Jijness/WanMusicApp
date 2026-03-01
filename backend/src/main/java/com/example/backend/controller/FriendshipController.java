package com.example.backend.controller;

import com.example.backend.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/friendship")
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipService friendshipService;

    @PostMapping("/addFriend/{id}")
    public ResponseEntity<String> addFriend(Long id){
        return ResponseEntity.ok(friendshipService.addFriend(id));
    }

    @DeleteMapping("/deleteFriend/{id}")
    public ResponseEntity<String> deleteFriend(Long id){
        return ResponseEntity.ok(friendshipService.deleteFriend(id));
    }

}
