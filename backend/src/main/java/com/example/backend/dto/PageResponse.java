package com.example.backend.dto;

import java.util.List;

public record PageResponse <T> (
        List<T> content,
        int currentPage,
        int pageSize,
        long totalElements,
        int totalPages
) {}
