package com.example.backend.dto;

import lombok.Data;

@Data
public class PresignedUploadRequestDTO {
    private String fileName;
    private String fileType;
    private String bucketName;
}
