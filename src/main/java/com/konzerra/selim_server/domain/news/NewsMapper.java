package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.news.dto.DetailedNewsDto;
import com.konzerra.selim_server.domain.news.dto.NewsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    NewsDto mapToDto(News entity);
    DetailedNewsDto mapToDetailedDto(News entity); // think over function name

}
