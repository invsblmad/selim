package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.news.dto.NewsDetailsDto;
import com.konzerra.selim_server.domain.news.dto.NewsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsService {
    Page<NewsDto> getAllNews(Pageable pageable);
    NewsDetailsDto getNewsById(int newsId);
    Page<NewsDto> getSimilarNewsTo(int newsId);
}
