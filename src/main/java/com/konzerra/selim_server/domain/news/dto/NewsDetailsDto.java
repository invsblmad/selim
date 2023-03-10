package com.konzerra.selim_server.domain.news.dto;

public record NewsDetailsDto(
        int id,
        String title,
        String coverImage,
        String text,
        String contentImage
) {
}
