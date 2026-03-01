package com.example.backend.dto.authentication;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken,
        String message
) {}
