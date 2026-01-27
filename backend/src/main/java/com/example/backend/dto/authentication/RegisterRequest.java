package com.example.backend.dto.authentication;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String displayName;
}
