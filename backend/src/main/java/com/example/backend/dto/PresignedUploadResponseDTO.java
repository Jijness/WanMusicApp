package com.example.backend.dto;

import lombok.Data;

@Data
public class PresignedUploadResponseDTO {
    private String url;
    private String key;
}
