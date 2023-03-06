package com.konzerra.selim_server.domain.news.impl;

import com.konzerra.selim_server.domain.news.News;
import com.konzerra.selim_server.domain.news.NewsMapper;
import com.konzerra.selim_server.domain.news.dto.DetailedNewsDto;
import com.konzerra.selim_server.domain.news.dto.NewsDto;

public class NewsMapperImpl implements NewsMapper {
    @Override
    public NewsDto mapToDto(News news) {
        return new NewsDto(
                news.getId(),
                news.getTitle(),
                news.getCoverImage()
        );

    }
    @Override
    public DetailedNewsDto mapToDetailedDto(News news) {
        return new DetailedNewsDto(
                news.getId(),
                news.getTitle(),
                news.getCoverImage(),
                news.getText(),
                news.getContentImage()
        );
    }
}
