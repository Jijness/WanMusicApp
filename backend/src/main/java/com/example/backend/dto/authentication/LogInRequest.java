package com.example.backend.dto.authentication;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LogInRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
