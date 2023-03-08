package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.news.dto.NewsDetailsDto;
import com.konzerra.selim_server.domain.news.dto.NewsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    NewsDto newsEntityToDto(News news);
    NewsDetailsDto newsEntityToDetailsDto(News news);

}
