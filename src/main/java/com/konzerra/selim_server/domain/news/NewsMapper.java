package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.news.dto.NewsDetailsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsRequest;
import com.konzerra.selim_server.domain.news.dto.NewsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsResponse newsEntityToDto(News news);
    NewsDetailsResponse newsEntityToDetailsDto(News news);
    News newsDtoToEntity(NewsRequest newsRequest);

}
