package com.example.backend.dto.user;

import lombok.Data;

@Data
public class UserUpdateProfileDTO {
    private String displayName;
    private String avatarKey;
}
