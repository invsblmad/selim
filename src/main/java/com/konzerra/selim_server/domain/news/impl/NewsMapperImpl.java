package com.konzerra.selim_server.domain.news.impl;

import com.konzerra.selim_server.domain.news.News;
import com.konzerra.selim_server.domain.news.NewsMapper;
import com.konzerra.selim_server.domain.news.dto.DetailedNewsDto;
import com.konzerra.selim_server.domain.news.dto.NewsDto;

public class NewsMapperImpl implements NewsMapper {
    @Override
    public NewsDto mapToDto(News entity) {
        return new NewsDto(
                entity.getId(),
                entity.getTitle(),
                entity.getCoverImage()
        );

    }
    @Override
    public DetailedNewsDto mapToDetailedDto(News entity) {
        return new DetailedNewsDto(
                entity.getId(),
                entity.getTitle(),
                entity.getCoverImage(),
                entity.getText(),
                entity.getContentImage()
        );
    }
}
