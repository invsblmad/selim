package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.common.StatusResponse;
import com.konzerra.selim_server.domain.news.dto.NewsDetailsDto;
import com.konzerra.selim_server.domain.news.dto.NewsDto;
import com.konzerra.selim_server.domain.news.dto.NewsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface NewsService {
    Page<NewsDto> getAllNews(Pageable pageable);
    NewsDetailsDto getNewsById(int newsId);
    Page<NewsDto> getSimilarNewsTo(int newsId);

    ResponseEntity<StatusResponse> saveNews(NewsRequest newsRequest);
    ResponseEntity<StatusResponse> updateNews(int id, NewsRequest newsRequest);
}
