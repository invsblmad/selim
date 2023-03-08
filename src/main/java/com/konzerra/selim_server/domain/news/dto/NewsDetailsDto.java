package com.konzerra.selim_server.domain.news.dto;

public record DetailedNewsDto(
        int id,
        String title,
        String coverImage,
        String text,
        String contentImage
) {
}
